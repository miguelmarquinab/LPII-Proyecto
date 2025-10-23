package com.lp2.sisinventario.sistemainventario.controller;

import jakarta.persistence.*;
import lombok.*;

import com.lp2.sisinventario.sistemainventario.dto.ModeloRequest;
import com.lp2.sisinventario.sistemainventario.service.ModeloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/modelos")
@RequiredArgsConstructor
public class ModeloController {

    //@Autowired
    private final ModeloService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("modelos", service.listar());
        return "modelo/list"; // templates/modelo/list.html
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("modelo", new ModeloRequest());
        model.addAttribute("titulo", "Nuevo Modelo");
        return "modelo/form"; // templates/modelo/form.html
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("modelo") ModeloRequest req,
                        BindingResult br,
                        RedirectAttributes ra,
                        Model model) {
        if (br.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Modelo");
            return "modelo/form";
        }
        service.crear(req);
        ra.addFlashAttribute("success", "Modelo creado correctamente");
        return "redirect:/modelos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        var dto = service.obtener(id);
        var req = new ModeloRequest();
        req.setId(dto.getId());
        req.setDescripcion(dto.getDescripcion());
        req.setCodigo(dto.getCodigo());
        model.addAttribute("modelo", req);
        model.addAttribute("titulo", "Editar Modelo");
        return "modelo/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("modelo") ModeloRequest req,
                             BindingResult br,
                             RedirectAttributes ra,
                             Model model) {
        if (br.hasErrors()) {
            model.addAttribute("titulo", "Editar Modelo");
            return "modelo/form";
        }
        req.setId(id);
        service.actualizar(req);
        ra.addFlashAttribute("success", "Modelo actualizado");
        return "redirect:/modelos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        service.eliminar(id);
        ra.addFlashAttribute("success", "Modelo eliminado");
        return "redirect:/modelos";
    }
}
