package com.sunedu.api.controller;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sunedu.api.model.Usuario;
import com.sunedu.api.repositories.ClienteRepository;
import com.sunedu.api.repositories.UsuarioRepository;

@Controller
@RequestMapping(value = "app")
public class UsuarioController {
    private static final String LOGIN_INDEX = "login";
    private static final String INDEX = "inicio";  
    private static String MODEL_CONTACT="user";
    private static String MODEL_MESSAGE="mensaje";
    private final UsuarioRepository usuariosData;
    
    public UsuarioController(UsuarioRepository usuariosData){
        this.usuariosData = usuariosData;
    } 
    
    @GetMapping("/inicio")
    public String getIndex(Model model){
        model.addAttribute("user", new Usuario());
        return INDEX;
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("user", new Usuario());
        return LOGIN_INDEX;
    }

    @PostMapping("/login")
    public String loginSubmitForm(Model model,
    @Valid Usuario objUser, 
    HttpServletRequest request, 
    BindingResult result )
    {
        model.addAttribute(MODEL_CONTACT, new Usuario());
        if(result.hasFieldErrors()) {
            model.addAttribute(MODEL_MESSAGE, "No se ha podido Iniciar la Sesión, por favor intentelo nuevamente.");
        }else{
            Optional<Usuario> userDB = this.usuariosData.findById(objUser.getAdminUser());
            if(userDB.isPresent()){
                if(userDB.get().getPassword().equals(objUser.getPassword())){
                    model.addAttribute(MODEL_CONTACT, userDB.get());
                    model.addAttribute(MODEL_MESSAGE, "La sesión fue iniciada correctamente");
                    request.getSession().setAttribute("user", objUser);
                } else {
                    model.addAttribute(MODEL_MESSAGE, "La contraseña no es válida");
                    return LOGIN_INDEX;
                }
            } else {
                model.addAttribute(MODEL_MESSAGE, "Usuario no existe");    
                return LOGIN_INDEX;
            }
        }
        return INDEX;             
    }

    @GetMapping("/logout")
	public String logoutSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/app/inicio";
	}
}
