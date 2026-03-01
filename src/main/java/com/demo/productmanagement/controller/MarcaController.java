package com.demo.productmanagement.controller;

import com.demo.productmanagement.model.Marca;
import com.demo.productmanagement.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // List all marcas
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("marcas", marcaService.listarTodosLasMarcas());
        return "marcas/list";
    }

    // Show form to create a new marca
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("marca", new Marca());
        return "marcas/form";
    }

    // Save a new marca
    @PostMapping
    public String saveMarca(@ModelAttribute Marca marca, RedirectAttributes redirectAttributes) {
        marcaService.guardarMarca(marca);
        redirectAttributes.addFlashAttribute("message", "Marca guardado con éxito");
        return "redirect:/marcas";
    }

    // Show form to edit a marca
    @GetMapping("/edit/{id}")
    public String showEditMarcaForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Marca> optionalMarca = marcaService.obtenerMarcaPorId(id);

        if (optionalMarca.isPresent()) {
            model.addAttribute("marca", optionalMarca.get());
            return "marcas/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Marca no encontrado");
            return "redirect:/marcas";
        }
    }

    // Update a marca
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Marca marca, RedirectAttributes redirectAttributes) {
        boolean updated = marcaService.actualizarMarca(marca);

        if (updated) {
            redirectAttributes.addFlashAttribute("message", "Marca actualizado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el Marca");
        }

        return "redirect:/marcas";
    }

    // View a marca
    @GetMapping("/view/{id}")
    public String viewMarca(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Marca> optionalMarca = marcaService.obtenerMarcaPorId(id);

        if (optionalMarca.isPresent()) {
            model.addAttribute("marca", optionalMarca.get());
            return "marcas/view";
        } else {
            redirectAttributes.addFlashAttribute("error", "Marca no encontrado");
            return "redirect:/marcas";
        }
    }

    // Delete a marca
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = marcaService.eliminarMarca(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Marca eliminado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el Marca");
        }

        return "redirect:/marcas";
    }
}