package com.ejemplo.act1_introduccion_spring_boot.controllers;

import com.ejemplo.act1_introduccion_spring_boot.dtos.FiguraDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/figuras")
public class FiguraController {

    private static List<FiguraDTO> listaFiguras = new ArrayList<>();

    static {
        if (listaFiguras.isEmpty()) {
            listaFiguras.add(new FiguraDTO(1L, "Goku Super Saiyan", "Dragon Ball Z", 45.0));
            listaFiguras.add(new FiguraDTO(2L, "Naruto Uzumaki", "Naruto Shippuden", 40.0));
            listaFiguras.add(new FiguraDTO(3L, "Luffy Gear 5", "One Piece", 60.0));
        }
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(name = "anime", required = false) String anime, Model model) {
        List<FiguraDTO> resultado = listaFiguras;
        if (anime != null && !anime.isEmpty()) {
            resultado = listaFiguras.stream()
                    .filter(f -> f.getAnime().equalsIgnoreCase(anime))
                    .collect(Collectors.toList());
        }
        model.addAttribute("figuras", resultado);
        model.addAttribute("desarrollador", "Samuel Perez Ramirez");
        model.addAttribute("nombreProyecto", "Actividad 2 - MVC y Thymeleaf (SPR)");
        return "figuras/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        FiguraDTO figura = listaFiguras.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("figura", figura);
        model.addAttribute("desarrollador", "Samuel Perez Ramirez");
        return "figuras/detalle";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("figura", new FiguraDTO());
        model.addAttribute("desarrollador", "Samuel Perez Ramirez");
        return "figuras/formulario";
    }

    @PostMapping("/guardar")
    public String guardarFigura(@ModelAttribute("figura") FiguraDTO figura) {
        figura.setId((long) (listaFiguras.size() + 1));
        listaFiguras.add(figura);
        return "redirect:/figuras/listar";
    }

    @PostMapping("/api/crear")
    @ResponseBody
    public FiguraDTO crearRest(@RequestBody FiguraDTO nuevaFigura) {
        nuevaFigura.setId((long) (listaFiguras.size() + 1));
        listaFiguras.add(nuevaFigura);
        return nuevaFigura;
    }
}