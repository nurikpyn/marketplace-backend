package br.com.vfs.marketplacebackend.service;

import br.com.vfs.marketplacebackend.dto.Image;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl {

//    private final String containerName;
//    private final CloudStorageAccount cloudStorageAccount;
//
//    @Autowired
//    public ImageServiceImpl(@Value("${azure.container-blob}") final String containerName,
//                            final CloudStorageAccount cloudStorageAccount) {
//        this.containerName = containerName;
//        this.cloudStorageAccount = cloudStorageAccount;
//    }

    public String saveBlobFile(Image image) {
//        try
//        {
//            // Create a blob client.
//            final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
//            // Get a reference to a container. (Name must be lower case.)
//            final CloudBlobContainer container = blobClient.getContainerReference(containerName);
//            // Get a blob reference for a name file.
//            CloudBlockBlob blob = container.getBlockBlobReference(generateName(image));
//            blob.uploadFromByteArray(image.getBytes(), 0, image.getBytes().length);
//            return blob.getUri().toString();
//        }
//        catch (Exception e) {
//           throw  new ImageException(e);
//        }
        return "";
    }

    private String generateName(final Image image) {
        return String.format("%s.%s", UUID.randomUUID(), image.getType());
    }
}
