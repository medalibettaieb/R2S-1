package tn.esprit.R2S.resource;

import org.apache.commons.lang3.StringUtils;
import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/api/email-model")
//@Secured(Roles.RECRUITMENT_MANAGER)
public class EmailModelResource {


    @EJB
    private IEmailModelService emailModelService;

    @EJB
    private ICandidateService candidateService;

    @EJB
    private IJobService jobService;

    @EJB
    private IJobFieldService jobFieldService;

    @EJB
    private ICandidateFieldService candidateFieldService;


    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "emailServiceEJB", lookup = "java:/jms/queue/R2S")
    private Queue emailServiceEJB;

    @GET
    @Path("/send/{email-model-id}/{candidate-cin}/{job-id}")
    public Response sendEmail(@PathParam("email-model-id") Long emailModelId,
                              @PathParam("candidate-cin") Long cin,
                              @PathParam("job-id") Long jobId) throws JMSException {

        EmailModel emailModel = emailModelService.find(emailModelId);
        if (emailModel == null) {
            throw new NotFoundException("Email model not found");
        }

        Candidate candidate = candidateService.find(cin);
        if (candidate == null) {
            throw new NotFoundException("Candidate not found");
        }

        Job job = jobService.find(jobId);
        if (job == null) {
            throw new NotFoundException("Job not found");
        }

        try {
            System.out.println(parseEmail(emailModel, candidate, job));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*final Connection connection = connectionFactory.createConnection();

        connection.start();

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final MessageProducer emails = session.createProducer(emailServiceEJB);


        HashMap<String, String> messages = new HashMap<>();

        messages.put("recipient", candidate.getEmail());
        messages.put("subject", "this is a subject");
        messages.put("content", parseEmail(emailModel, candidate, job));

        emails.send(session.createObjectMessage(messages));

        connection.close();*/

        return null;
    }

    @POST
    public Response createEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.create(emailModel);
        return Response.created(new URI("/resources/api/email-model/" + emailModel.getId())).entity(emailModel).build();
    }

    @PUT
    public Response updateEmailModel(EmailModel emailModel) throws URISyntaxException {

        emailModelService.edit(emailModel);
        return Response.ok().entity(emailModel).build();
    }

    @GET
    public List<EmailModel> getAllEmailModels() {

        return emailModelService.findAll();
    }

    @Path("/{id}")
    @GET
    public Response getEmailModel(@PathParam("id") Long id) {

        return Optional.ofNullable(emailModelService.find(id))
                .map(emailModel -> Response.ok(emailModel).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    public Response removeEmailModel(@PathParam("id") Long id) {

        return Optional.ofNullable(emailModelService.find(id))
                .map(emailModel -> {
                    emailModelService.remove(emailModel);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);
    }

    @Path("/variables")
    @GET
    public Response getVariables() {

        JsonObjectBuilder variables = Json.createObjectBuilder();

        Field[] user = Users.class.getDeclaredFields();
        Field[] address = Address.class.getDeclaredFields();
        Field[] job = Job.class.getDeclaredFields();

        //candidate fields
        JsonObjectBuilder candidateObjectBuilder = Json.createObjectBuilder();
        for (Field field : user) {
            if (!field.getName().contains("address")) {
                candidateObjectBuilder.add(field.getName(), "{{candidate." + field.getName() + "}}");
            }
        }

        //address fields
        JsonObjectBuilder addressObjectBuilder = Json.createObjectBuilder();
        for (Field field : address) {
            addressObjectBuilder.add(field.getName(), "{{candidate.address." + field.getName() + "}}");
        }

        candidateObjectBuilder.add("Address", addressObjectBuilder.build());

        //extra fields
        JsonObjectBuilder candidateExtraObjectBuilder = Json.createObjectBuilder();
        for (CandidateField candidateField : candidateFieldService.findAll()) {
            candidateExtraObjectBuilder.add(candidateField.getFieldName(), "{{candidate.extra." + candidateField.getFieldName() + "}}");
        }
        candidateObjectBuilder.add("Extra", candidateExtraObjectBuilder.build());

        //sum up
        variables.add("Candidate", candidateObjectBuilder.build());

        //referee fields
        JsonObjectBuilder refereeObjectBuilder = Json.createObjectBuilder();
        for (Field field : user) {
            if (!field.getName().contains("address")) {
                refereeObjectBuilder.add(field.getName(), "{{referee." + field.getName() + "}}");
            }
        }

        variables.add("Referee", refereeObjectBuilder.build());

        //job fields
        JsonObjectBuilder jobObjectBuilder = Json.createObjectBuilder();
        for (Field field : job) {
            System.out.println(field + ", " + field.getType().getPackage().getName());
            if (field.getType() != List.class) {
                jobObjectBuilder.add(field.getName(), "{{job." + field.getName() + "}}");
            }
        }

        //extra fields
        JsonObjectBuilder jobExtraObjectBuilder = Json.createObjectBuilder();
        for (JobField jobField : jobFieldService.findAll()) {
            jobExtraObjectBuilder.add(jobField.getFieldName(), "{{job.extra." + jobField.getFieldName() + "}}");
        }

        jobObjectBuilder.add("Extra", jobExtraObjectBuilder.build());

        //sum up
        variables.add("Job", jobObjectBuilder.build());
        return Response.ok(variables.build()).build();
    }


    private String parseEmail(EmailModel emailModel, Candidate candidate, Job job) throws Exception {

        String content = emailModel.getContent();

        String regex = "\\{\\{(.*?)\\}\\}";

        List<String> keywords = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            keywords.add(matcher.group());
        }

        for (String keyword : keywords) {
            String[] fields = keyword.replaceAll("[\\{\\}]", "").split("\\.");
            switch (fields[0]) {
                case "candidate":
                    if (fields.length == 2) {
                        content = StringUtils.replace(content,
                                keyword,
                                Users.class.getMethod("get" + StringUtils.capitalize(fields[1]))
                                        .invoke(candidate).toString()
                        );

                    } else if (fields.length == 3 && fields[1].equals("address")) {
                        Object obj = Users.class.getMethod("get" + StringUtils.capitalize(fields[1])).invoke(candidate);
                        String value = Address.class.getMethod("get" + StringUtils.capitalize(fields[2])).invoke(obj).toString();
                        content = StringUtils.replace(content, keyword, value);
                    } else if (fields.length == 3 && fields[1].equals("extra")) {

                        CandidateField candidateField = candidateFieldService.findByName(fields[2]);
                        CandidateFieldValue value = candidateFieldService.findValue(candidateField, candidate);
                        if (value != null) {
                            content = StringUtils.replace(content, keyword, value.getValue());
                        }
                    }
                    break;
                case "referee":
                    content = StringUtils.replace(content,
                            keyword,
                            Users.class.getMethod("get" + StringUtils.capitalize(fields[1]))
                                    .invoke(candidate.getReferee()).toString()
                    );
                    break;
                case "job":
                    if (fields.length == 2) {
                        content = StringUtils.replace(content,
                                keyword,
                                Job.class.getMethod("get" + StringUtils.capitalize(fields[1]))
                                        .invoke(job).toString()
                        );

                    } else if (fields.length == 3 && fields[1].equals("extra")) {
                        JobField jobField = jobFieldService.findByName(fields[2]);
                        JobFieldValue value = jobFieldService.findValue(jobField, job);
                        if (value != null) {
                            content = StringUtils.replace(content, keyword, value.getValue());
                        }
                    }
                    break;
            }
        }

        return content;
    }
}
