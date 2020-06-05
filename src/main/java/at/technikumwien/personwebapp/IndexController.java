package at.technikumwien.personwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping
    public String index(
            @RequestParam(defaultValue = "false") boolean all,
            Model model){ // hier bekommen wir das leere Model, das wir befüllen wollen
        // String weil es eine HTML Seite ist
        // das Model wird uns übergeben von Spring
        var persons = (all ? personRepository.findAll() :
                personRepository.findAllByActiveTrue());
        model.addAttribute("persons", persons);
        if(all){
            model.addAttribute("all", true); // zusätzlich wollen wir noch dieses
            // Attribute im Model, das Model wird dann an die View übergeben
        }
        return "index"; // so heißt die view
    }
}
