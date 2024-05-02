package org.nrg.xnatx.plugins.NIRS.sessionBuilder;

import lombok.extern.slf4j.Slf4j;
import org.nrg.session.SessionBuilder;
import org.nrg.xdat.XDAT;
import org.nrg.xdat.bean.NirsNirsscandataBean;
import org.nrg.xdat.bean.NirsNirssessiondataBean;
import org.nrg.xdat.bean.XnatImagesessiondataBean;
import org.nrg.xdat.model.XnatImagescandataI;
import org.nrg.xdat.om.NirsNirssessiondata;
import org.nrg.xnat.helpers.prearchive.PrearcUtils;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class NIRSSessionBuilder extends SessionBuilder {

    private final File sessionDir;

    public NIRSSessionBuilder(final File sessionDir, final Writer fileWriter) {
        super(sessionDir, sessionDir.getPath(), fileWriter);
        this.sessionDir = sessionDir;
    }

    @Override
    public String getSessionInfo() {
        return "(undetermined)";
    }

    @Override
    public XnatImagesessiondataBean call() throws Exception {
        // Get proj/subj/sess/... parameters
        Map<String, String> parameters = getParameters();
        String project = parameters.getOrDefault(PrearcUtils.PARAM_PROJECT, null);
        String subject = parameters.getOrDefault(PrearcUtils.PARAM_SUBJECT_ID, "");
        String label = parameters.getOrDefault(PrearcUtils.PARAM_LABEL, null);

        log.debug("Building BLI session for Project: {} Subject: {} Session: {}", project, subject, label);

        // Initialize the session and populate
        NirsNirssessiondataBean NIRSSession = new NirsNirssessiondataBean();

        NIRSSession.setPrearchivepath(sessionDir.getPath());
        NIRSSession.setProject(project);
        NIRSSession.setSubjectId(subject);
        NIRSSession.setLabel(label);


        // Build scans
        Path scanDir = sessionDir.toPath().resolve("SCANS");
        List<Path> scans = Files.list(scanDir).filter(Files::isDirectory).collect(Collectors.toList());

        for (Path scan : scans) {
            final NIRSScanBuilder NIRSScanBuilder = new NIRSScanBuilder(scan);
            NIRSSession.addScans_scan(NIRSScanBuilder.call());
        }

//         Set session date
//        Optional<Date> sessionDate = NIRSSession.getScans_scan().stream()
//                .map(XnatImagescandataI::getStartDate)
//                .map(d -> (Date) d)
//                .distinct()
//                .sorted()
//                .findFirst();
//        sessionDate.ifPresent(NIRSSession::setDate);

        // Set operator
//        Optional<String> operator = NIRSSession.getScans_scan().stream()
//                .map(XnatImagescandataI::getOperator)
//                .distinct()
//                .findFirst();
//        operator.ifPresent(NIRSSession::setOperator);

        return NIRSSession;
    }
}
