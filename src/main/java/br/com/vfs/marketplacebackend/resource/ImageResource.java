package br.com.vfs.marketplacebackend.resource;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images/public")
public class ImageResource {

    final String containerName = "posgraduacao-imagens";

    @Autowired
    private CloudStorageAccount cloudStorageAccount;
    
    @GetMapping(value = "/")
    public String readBlobFile() throws IOException {
        try
        {
            // Create a blob client.
            final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
            // Get a reference to a container. (Name must be lower case.)
            final CloudBlobContainer container = blobClient.getContainerReference(containerName);
            // Get a blob reference for a text file.
            CloudBlockBlob blob = container.getBlockBlobReference("philadelphia.jpg");
            return blob.getUri().toString();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping(value = "/")
    public String saveBlobFile(@RequestParam("file") MultipartFile multipart) throws IOException {
        try
        {
            // Create a blob client.
            final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
            // Get a reference to a container. (Name must be lower case.)
            final CloudBlobContainer container = blobClient.getContainerReference(containerName);
            // Get a blob reference for a name file.
            CloudBlockBlob blob = container.getBlockBlobReference(multipart.getOriginalFilename());
            blob.uploadFromByteArray(multipart.getBytes(), 0, multipart.getBytes().length);
            return blob.getUri().toString();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
            return "error";
        }
    }
}
