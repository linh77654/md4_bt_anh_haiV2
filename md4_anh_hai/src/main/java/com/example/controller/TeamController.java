    package com.example.controller;

    import com.example.baitap_md4.model.Team;
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
    @RequestMapping("/team")
    public class TeamController {
        @Autowired
        private ITeamService teamService;

        @GetMapping("")
        public String getAllTeam(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
            Page<Team> teamPage = teamService.findAllPaginated(page);
            model.addAttribute("team", teamPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", teamPage.getTotalPages());
            return "team/listTeam";
        }

        @GetMapping("/create")
        public String createForm(Model model) {
            model.addAttribute("team", new Team());
            return "team/createTeam";
        }

        @PostMapping("/create")
        public String createTeam(@Validated @ModelAttribute("team") Team team,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "team/createTeam";
            }
            teamService.add(team);
            redirectAttributes.addFlashAttribute("message", "Thêm đội bóng thành công!");
            return "redirect:/team";
        }

        @GetMapping("/{id}/players")
        public String showTeamPlayers(@PathVariable("id") Integer id, Model model) {
            Team team = teamService.findById(id);
            if (team == null) {
                return "redirect:/team";
            }
            model.addAttribute("team", team);
            model.addAttribute("player", team.getPlayers());
            return "team/teamPlayer";
        }

        @PostMapping("/{id}/delete")
        public String deleteTeam(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
            Team team = teamService.findById(id);
            if (team == null) {
                redirectAttributes.addFlashAttribute("error", "Đội bóng không tồn tại!");
                return "redirect:/team";
            }

            if (team.getPlayers() != null && !team.getPlayers().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Không thể xóa đội bóng vì có thành viên trong đội!");
                return "redirect:/team";
            }

            teamService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Xóa đội bóng thành công!");
            return "redirect:/team";
        }
    }
