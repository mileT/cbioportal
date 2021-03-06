package org.cbioportal.persistence;

import org.cbioportal.model.SampleList;
import org.cbioportal.model.meta.BaseMeta;

import java.util.List;

public interface SampleListRepository {

    List<SampleList> getAllSampleLists(String projection, Integer pageSize, Integer pageNumber, String sortBy,
                                       String direction);

    BaseMeta getMetaSampleLists();

    SampleList getSampleList(String sampleListId);

    List<SampleList> getAllSampleListsInStudy(String studyId, String projection, Integer pageSize, Integer pageNumber,
                                              String sortBy, String direction);

    BaseMeta getMetaSampleListsInStudy(String studyId);

    List<String> getAllSampleIdsInSampleList(String sampleListId);
}
