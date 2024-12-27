package com.example.baitap_md4.controller;

import com.example.baitap_md4.model.Player;
import com.example.baitap_md4.model.Position;
import com.example.baitap_md4.model.Team;
import com.example.baitap_md4.service.IPlayerService;
import com.example.baitap_md4.service.IPositionService;
import com.example.baitap_md4.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ITeamService teamService;

    @Autowired
    private IPositionService positionService;

    @GetMapping("")
    public String getAllPlayers(Model model,
                                @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Player> playersPage = playerService.findAllPaginated(page);

        model.addAttribute("player", playersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", playersPage.getTotalPages());
        return "player/list";
    }

    @GetMapping("/create")
    public String createPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("position", positionService.getAll());
        model.addAttribute("team", teamService.getAll());
        return "player/create";
    }

    @PostMapping("/create")
    public String createPlayer(@Validated @ModelAttribute("player") Player player,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("position", positionService.getAll());
            model.addAttribute("team", teamService.getAll());
            return "player/create";
        }

        playerService.save(player);
        redirectAttributes.addFlashAttribute("message", "Thêm cầu thủ thành công!");
        return "redirect:/player";
    }

    @GetMapping("/{id}/delete")
    public String deletePlayer(@PathVariable(name = "id") Integer id,
                               RedirectAttributes redirectAttributes) {
        playerService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Xoá cầu thủ thaành công");
        return "redirect:/player";
    }

    @GetMapping("/{id}/edit")
    public String editPlayer(@PathVariable(name = "id") Integer id, Model model) {
        Player player = playerService.findById(id);
        if(player == null) {
            return "redirect:/player";
        }
        model.addAttribute("player", player);
        model.addAttribute("position", positionService.getAll());
        model.addAttribute("team", teamService.getAll());
        return "player/edit";
    }

    @PostMapping("/edit")
    public String updatePlayer(@Validated @ModelAttribute("player") Player player,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("position", positionService.getAll());
            model.addAttribute("team", teamService.getAll());
            return "player/edit";
        }

        playerService.save(player);
        redirectAttributes.addFlashAttribute("message", "Cập nhật cầu thủ thành công!");
        return "redirect:/player";
    }
}
