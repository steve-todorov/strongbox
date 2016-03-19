package org.carlspring.strongbox.storage.indexing;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mtodorov
 */
@Singleton
@Component
public class RepositoryIndexManager
{

    /**
     * K: storage/Id:repositoryId
     * V: index
     */
    private static final Logger logger = LoggerFactory.getLogger(RepositoryIndexManager.class);

    private Map<String, RepositoryIndexer> indexes = new LinkedHashMap<>();


    public RepositoryIndexManager()
    {
    }

    @PreDestroy
    private void close()
    {
        for (String storageAndRepository : indexes.keySet())
        {
            try
            {
                closeIndexer(storageAndRepository);
            }
            catch (IOException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void closeIndexer(String storageId, String repositoryId)
            throws IOException
    {
        closeIndexer(storageId + ":" + repositoryId);
    }

    public void closeIndexer(String storageIdAndRepositoryId)
            throws IOException
    {
        final RepositoryIndexer repositoryIndexer = indexes.get(storageIdAndRepositoryId);

        logger.debug("Closing indexer for " + repositoryIndexer.getStorageId() + ":" + repositoryIndexer.getRepositoryId() + "...");

        repositoryIndexer.close();

        logger.debug("Closed indexer for " + repositoryIndexer.getStorageId() + ":" + repositoryIndexer.getRepositoryId() + ".");

        indexes.remove(storageIdAndRepositoryId);
    }

    public Map<String, RepositoryIndexer> getIndexes()
    {
        return indexes;
    }

    public void setIndexes(Map<String, RepositoryIndexer> indexes)
    {
        this.indexes = indexes;
    }

    public RepositoryIndexer getRepositoryIndex(String storageId, String repositoryId)
    {
        return indexes.get(storageId + ":" + repositoryId);
    }

    public RepositoryIndexer addRepositoryIndex(String storageId, String repositoryId, RepositoryIndexer value)
    {
        return indexes.put(storageId + ":" + repositoryId, value);
    }

    public RepositoryIndexer removeRepositoryIndex(String storageId, String repositoryId)
    {
        return indexes.remove(storageId + ":" + repositoryId);
    }

}
