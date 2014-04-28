package racoonsoft.businesswin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import racoonsoft.businesswin.controller.api.BusinessWinController;
import racoonsoft.businesswin.database.PostgresqlDataSource;
import racoonsoft.businesswin.structure.data.*;
import racoonsoft.businesswin.structure.enums.CompanySellReason;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.businesswin.structure.model.GameWorld;
import racoonsoft.businesswin.structure.model.GoodsDeclaration;
import racoonsoft.businesswin.structure.model.Player;
import racoonsoft.library.access.User;
import racoonsoft.library.json.JSONProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class FaceController extends BusinessWinController
{
    @RequestMapping("")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return new ModelAndView("main");
    }

}
