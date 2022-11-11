package pl.sobota.imagesenderspringboot.gui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobota.imagesenderspringboot.model.Image;
import pl.sobota.imagesenderspringboot.repository.ImageRepository;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepository imageUpader;

    @Autowired
    public GalleryGui(ImageRepository imageUpader) {
        this.imageUpader = imageUpader;
        List<Image> all = imageUpader.findAll();
        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAdress(), "brak");
            add(image);
        } );
    }
}
