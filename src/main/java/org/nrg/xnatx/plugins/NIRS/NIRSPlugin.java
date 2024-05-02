package org.nrg.xnatx.plugins.NIRS;

import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.annotations.XnatDataModel;
import org.nrg.framework.annotations.XnatPlugin;
import org.nrg.xdat.om.NirsNirssessiondata;
import org.nrg.xdat.om.NirsNirsscandata;
import org.nrg.xnat.restlet.actions.importer.ImporterHandlerPackages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@XnatPlugin(value = "NIRSPlugin", name = "NIRS Plugin",
            logConfigurationFile = "NIRS-logback.xml",
            dataModels = {
                          @XnatDataModel(value = NirsNirssessiondata.SCHEMA_ELEMENT_NAME,
                                         singular = "NIRS Session",
                                         plural = "NIRS Sessions",
                                         code = "NIRS"),
                          @XnatDataModel(value = NirsNirsscandata.SCHEMA_ELEMENT_NAME,
                                         singular = "NIRS Scan",
                                         plural = "NIRS Scans",
                                         code = "NIRSScan")
                          })
@ComponentScan({"org.nrg.xnatx.plugins.NIRS.bli.helpers",
                "org.nrg.xnatx.plugins.NIRS.bli.helpers.impl"
})
@Slf4j
public class NIRSPlugin {

    @Autowired
    public NIRSPlugin() { }

    @Bean
    public ImporterHandlerPackages pixiImporterHandlerPackages() {
        return new ImporterHandlerPackages("org.nrg.xnatx.plugins.NIRS.importer");
    }

}
