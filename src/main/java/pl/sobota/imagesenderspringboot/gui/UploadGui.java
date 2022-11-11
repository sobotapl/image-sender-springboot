package pl.sobota.imagesenderspringboot.gui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobota.imagesenderspringboot.service.ImageTransfer;

@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageTransfer imageTransfer;

    @Autowired
    public UploadGui(ImageTransfer imageTransfer) {
        this.imageTransfer = imageTransfer;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(clickEvent ->
        {
            String uploadedImage = imageTransfer.uploadFileAndSaveToDb(textField.getValue());
            Image image = new Image(uploadedImage, "NOK");
            label.setText("OK");
            add(label);
            add(image);

        });

        add(textField);
        add(button);

    }
}