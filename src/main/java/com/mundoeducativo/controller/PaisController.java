package com.mundoeducativo.controller;

import com.mundoeducativo.model.Pais;
import com.mundoeducativo.repository.PaisRepository;
import com.mundoeducativo.service.PaisService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PaisController {
    @Autowired
    private PaisRepository paisRepository;

    @GetMapping("/paises")
    public String mostrarPaises(Model model){
        List<Pais> lista = paisRepository.findAll();
        model.addAttribute("paises", paisRepository.findAll());
        return "paises"; //Carga paises.html
    }

    @PostConstruct
    public void cargarDatosIniciales(){
        if (paisRepository.count() == 0) { // evita duplicar al reiniciar
            Pais pais1 = new Pais();
            pais1.setNombre("Argentina");
            pais1.setContinente("América");
            pais1.setBanderaUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");

            System.out.println("Bandera Argentina: " + pais1.getBanderaUrl());


            Pais pais2 = new Pais();
            pais2.setNombre("Japón");
            pais2.setContinente("Asia");
            pais2.setBanderaUrl("https://upload.wikimedia.org/wikipedia/en/9/9e/Flag_of_Japan.svg");

            System.out.println("Bandera Japón: " + pais2.getBanderaUrl());

            paisRepository.save(pais1);
            paisRepository.save(pais2);
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pais", new Pais()); // crea un país vacío
        return "nuevo"; // vista nuevo.html
    }

    @PostMapping("/guardar")
    public String guardarPais(@Valid @ModelAttribute Pais pais, BindingResult result) {
        if (result.hasErrors()) {
            return "nuevo"; // vuelve al formulario si hay errores
        }
        paisRepository.save(pais);
        return "redirect:/paises"; // redirige a la lista actualizada
    }

    @Autowired
    private PaisService paisService;

    @GetMapping("/pais/{id}")
    public String verDetallePais(@PathVariable Long id, Model model) {
        Pais pais = paisService.buscarPorId(id);
        model.addAttribute("pais", pais);
        return "pais_detalle";
    }

    @GetMapping("/galeria")
    public String mostrarGaleria(Model model) {
        List<Pais> paises = paisService.obtenerTodos();
        model.addAttribute("paises", paises);
        return "galeria"; // sin extensión .html
    }

    @PostMapping("/pais/guardar")
    public String guardarPais(@ModelAttribute Pais pais) {
        paisService.guardar(pais); // esto inserta el nuevo país en tu base
        return "redirect:/galeria"; // redirige a la galería después de agregar
    }

    @GetMapping("/galeria/continente/{nombre}")
    public String filtrarPorContinente(@PathVariable String nombre, Model model) {
        List<Pais> paises = paisService.buscarPorContinente(nombre);
        model.addAttribute("paises", paises);
        model.addAttribute("continente", nombre);
        return "galeria";
    }


}

