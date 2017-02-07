package org.cbioportal.persistence.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cbioportal.model.Gene;
import org.cbioportal.model.Geneset;
import org.cbioportal.model.meta.BaseMeta;

public interface GenesetMapper {

    List<Geneset> getGenesets(@Param("projection") String projection,
                        @Param("limit") Integer limit,
                        @Param("offset") Integer offset,
                        @Param("sortBy") String sortBy,
                        @Param("direction") String direction);

    BaseMeta getMetaGenesets();

    Geneset getGenesetByInternalId(@Param("genesetId") Integer internalId,
            @Param("projection") String projection);
    
    Geneset getGenesetByGenesetId(@Param("genesetId") String genesetId,
                               @Param("projection") String projection);

    Geneset getGenesetByGeneticEntityId(@Param("geneticEntityId") Integer geneticEntityId,
            @Param("projection") String projection);

    List<Geneset> getGenesetsByGenesetIds(@Param("genesetIds") List<String> genesetIds,
            @Param("projection") String projection);
}
