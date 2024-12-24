package com.example.md4_anh_hai.controller;

import com.example.md4_anh_hai.model.Player;
import com.example.md4_anh_hai.service.IPlayerService;
import com.example.md4_anh_hai.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ILocationService locationService;

    @GetMapping("")
    public String viewAllPlayers(
            @RequestParam(defaultValue = "") String searchName,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            Model model) {

        Page<Player> playerPage = playerService.findByName(searchName, page, size);

        model.addAttribute("playerList", playerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", playerPage.getTotalPages());
        model.addAttribute("totalItems", playerPage.getTotalElements());
        model.addAttribute("searchName", searchName);
        model.addAttribute("size", size);

        return "view";

    }

    @GetMapping("/create")
    public String viewAddPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("locations", locationService.getAll());
        return "create";
    }

    @PostMapping("/save")
    public String addPlayer(@ModelAttribute("player") Player player,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("locations", locationService.getAll());
            return "player/add";
        }

        playerService.save(player);
        redirectAttributes.addFlashAttribute("message", "Thêm cầu thủ thành công!");
        return "redirect:/player";
    }

    @GetMapping("/edit")
    public String editPlayerForm(@RequestParam("id") Long playerId, Model model, RedirectAttributes redirectAttributes) {
        Player player = playerService.findById(playerId).orElse(null);
        if (player != null) {
            model.addAttribute("player", player);
            model.addAttribute("locations", locationService.getAll());
            return "player/edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
            return "redirect:/player";
        }
    }

    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes) {
        if (playerService.findById(player.getId()).isPresent()) {
            playerService.save(player);
            redirectAttributes.addFlashAttribute("message", "Cập nhật cầu thủ thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
        }
        return "redirect:/player";
    }

    @PostMapping("/delete")
    public String deletePlayer(@RequestParam("id") Long playerId, RedirectAttributes redirectAttributes) {
        if (playerService.findById(playerId).isPresent()) {
            playerService.remove(playerId);
            redirectAttributes.addFlashAttribute("message", "Xóa cầu thủ thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
        }
        return "redirect:/player";
    }
}
