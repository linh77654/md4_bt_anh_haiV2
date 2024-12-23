package com.example.md4_anh_hai.controller;

import com.example.md4_anh_hai.model.Player;
import com.example.md4_anh_hai.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    // Hiển thị danh sách cầu thủ
    @GetMapping("")
    public String viewAllPlayers(@RequestParam(defaultValue = "") String searchName, Model model) {
        model.addAttribute("playerList", playerService.getAll());
        model.addAttribute("searchName", searchName);
        return "view";
    }

    // Form tạo cầu thủ
    @GetMapping("/create")
    public String createPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", new String[]{"Forward", "Midfielder", "Defender", "Goalkeeper"});
        return "create";
    }

    // Lưu cầu thủ mới
    @PostMapping("/save")
    public String savePlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes) {
        try {
            playerService.save(player);
            redirectAttributes.addFlashAttribute("message", "Thêm cầu thủ thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu cầu thủ: " + e.getMessage());
        }
        return "redirect:/player";
    }

    // Form chỉnh sửa cầu thủ
    @GetMapping("/edit")
    public String editPlayerForm(@RequestParam("id") Long playerId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Player> playerOptional = playerService.findById(playerId);
        if (playerOptional.isPresent()) {
            model.addAttribute("player", playerOptional.get());
            model.addAttribute("positions", new String[]{"Forward", "Midfielder", "Defender", "Goalkeeper"});
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
            return "redirect:/player";
        }
    }

    // Cập nhật cầu thủ
    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes) {
        Optional<Player> existingPlayer = playerService.findById(player.getId());
        if (existingPlayer.isPresent()) {
            try {
                playerService.save(player);
                redirectAttributes.addFlashAttribute("message", "Cập nhật cầu thủ thành công!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật cầu thủ: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
        }
        return "redirect:/player";
    }

    // Xóa cầu thủ
    @PostMapping("/delete")
    public String deletePlayer(@RequestParam("id") Long playerId, RedirectAttributes redirectAttributes) {
        Optional<Player> playerOptional = playerService.findById(playerId);
        if (playerOptional.isPresent()) {
            try {
                playerService.remove(playerId);
                redirectAttributes.addFlashAttribute("message", "Xóa cầu thủ thành công!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa cầu thủ: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Cầu thủ không tồn tại!");
        }
        return "redirect:/player";
    }
}
