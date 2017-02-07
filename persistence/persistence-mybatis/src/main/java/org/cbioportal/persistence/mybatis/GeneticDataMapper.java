package org.cbioportal.persistence.mybatis;

import org.cbioportal.model.GeneticAlteration;
import org.cbioportal.model.GenesetAlteration;

import java.util.List;

public interface GeneticDataMapper {

    String getCommaSeparatedSampleIdsOfGeneticProfile(String geneticProfileId);

    List<GeneticAlteration> getGeneticAlterations(String geneticProfileId, List<Integer> entrezGeneIds, 
                                                  String projection);

	List<GenesetAlteration> getGenesetAlterations(String geneticProfileId, List<String> genesetIds,
			String projection);
}
