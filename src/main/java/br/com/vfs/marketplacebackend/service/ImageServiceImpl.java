package br.com.vfs.marketplacebackend.service;

import br.com.vfs.marketplacebackend.dto.Image;
import br.com.vfs.marketplacebackend.exception.ImageException;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl {

    private final String containerName;
    private final CloudStorageAccount cloudStorageAccount;

    @Autowired
    public ImageServiceImpl(@Value("${azure.container-blob}") final String containerName,
                            final CloudStorageAccount cloudStorageAccount) {
        this.containerName = containerName;
        this.cloudStorageAccount = cloudStorageAccount;
    }

    public String saveBlobFile(Image image) {
        try
        {
            // Create a blob client.
            final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
            // Get a reference to a container. (Name must be lower case.)
            final CloudBlobContainer container = blobClient.getContainerReference(containerName);
            // Get a blob reference for a name file.
            CloudBlockBlob blob = container.getBlockBlobReference(gerenateName(image));
            blob.uploadFromByteArray(image.getBytes(), 0, image.getBytes().length);
            return blob.getUri().toString();
        }
        catch (Exception e) {
           throw  new ImageException(e);
        }
    }

    private String gerenateName(final Image image) {
        return String.format("%s.%s", UUID.randomUUID(), image.getType());
    }
}
