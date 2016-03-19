package org.carlspring.strongbox.services;

import org.carlspring.strongbox.storage.Storage;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @author mtodorov
 */
public interface StorageManagementService
{

    void createStorage(Storage storage)
            throws IOException;

    void removeStorage(String storageId, boolean deleteRepositoryContents)
            throws IOException, JAXBException;

}
