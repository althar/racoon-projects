package racoonsoft.businesswin.service;

import org.springframework.stereotype.Service;
import racoonsoft.businesswin.structure.data.*;
import racoonsoft.businesswin.structure.enums.*;
import racoonsoft.businesswin.structure.model.*;
import racoonsoft.library.helper.Helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

@Service
public class GameService
{

    //<editor-fold desc="General">
    public HashMap<Long,Game> getGames()
    {
        return GameWorld.getGames();
    }
    public Game getGame(Long id) throws Exception
    {
        return GameWorld.getGame(id);
    }
    //</editor-fold>

    //<editor-fold desc="Phase 0">
    public StatusCode createGame(String name, GameMode mode,StartSettings start_settings,Integer companyCount) throws Exception
    {
        //TODO: check parameters...
        GameWorld.createGame(name, mode, start_settings,companyCount);

        return StatusCode.SUCCESS;
    }
    public StatusCode finishGame(Long game_id) throws Exception
    {
        GameWorld.finishGame(game_id);
        return StatusCode.SUCCESS;
    }
    public StatusCode startGame(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.status!=GameStatus.READY)
        {
            return StatusCode.GAME_NOT_READY;
        }
        Iterator<Long> plIter = g.players.keySet().iterator();
        // Distribute one for each...
        int company_index = 0;
        while(plIter.hasNext())
        {
            Long player_id = plIter.next();
            Player p = g.players.get(player_id);
            p.companies.clear();
            p.companies.add(g.companies.get(company_index));
            company_index++;
            g.players.put(player_id,p);
        }
        // Distribute else
        plIter = g.players.keySet().iterator();
        while(company_index<g.companies.size())
        {
            if(!plIter.hasNext())
            {
                plIter = g.players.keySet().iterator();
            }
            Long player_id = plIter.next();
            Player p = g.players.get(player_id);

            if(new Random().nextBoolean())// check random
            {
                p.companies.add(g.companies.get(company_index));
                company_index++;
                g.players.put(player_id,p);
            }
        }
        calculateStep0(g);
        g.turn.nextPhase();
        g.setStatus(GameStatus.IN_PROGRESS);
        return StatusCode.SUCCESS;
    }
    public StatusCode joinGame(Long game_id,Player player) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.status!=GameStatus.NEW&&g.status!=GameStatus.READY)
        {
            return StatusCode.GAME_ALREADY_STARTED;
        }
        if(g.companies.size()<=g.players.size())
        {
            return StatusCode.TOO_MANY_PLAYERS;
        }
        g.addPlayer(player);
        g.status = GameStatus.READY;
        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 1">
    public StatusCode declareGoods(Long game_id, Long player_id,Long company_id,GoodsDeclaration declaration) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        if(company_id==null)
        {
            for(Company c:p.getCompanies())
            {
                c.goodsDeclaration = declaration;
            }
        }
        Company c = p.getCompany(company_id);
        if(c == null)
        {
            return StatusCode.NO_SUCH_COMPANY;
        }

        c.goodsDeclaration = declaration;
        return StatusCode.SUCCESS;
    }
    public StatusCode setTradeFactors(Long game_id, TradeFactors tradeFactors) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        g.tradeFactors = tradeFactors;
        return StatusCode.SUCCESS;
    }
    public StatusCode endPhase1(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.turn.phase != 1)
        {
            return StatusCode.WRONG_PHASE;
        }
        calculatePhase1(g);
        g.turn.nextPhase();
        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 2">
    public StatusCode setEventCards(Long game_id, DeclaredEventCards eventCards) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        g.declared_event_cards = eventCards;
        return StatusCode.SUCCESS;
    }
    public StatusCode acceptCard(Long game_id, Long player_id,Long company_id,String card,String size,Credit credit) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        Company c = p.getCompany(company_id);
        if(c == null)
        {
            return StatusCode.NO_SUCH_COMPANY;
        }
        return acceptCard(c,card,size,g,credit);
    }
    public StatusCode sellCompany(Long game_id, Long player_id,Long company_id,CompanySellReason reason) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        Company c = p.getCompany(company_id);
        if(c == null)
        {
            return StatusCode.NO_SUCH_COMPANY;
        }
        sell(c,g,reason);
        return StatusCode.SUCCESS;
    }
    public StatusCode betCompany(Long game_id, Long player_id,Long company_id,CompanyBet bet) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        Company c = p.getCompany(company_id);
        if(c == null)
        {
            return StatusCode.NO_SUCH_COMPANY;
        }

        return bet(c,p,bet,g);
    }
    public StatusCode consolidation(Long game_id, Long player_id,Long company_id,Double value) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        Player p = g.players.get(player_id);
        if(p == null)
        {
            return StatusCode.NO_SUCH_PLAYER;
        }
        Company c = p.getCompany(company_id);
        if(c == null)
        {
            return StatusCode.NO_SUCH_COMPANY;
        }
        return consolidation(c,value,p,g);
    }
    public StatusCode endPhase2(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.turn.phase != 2)
        {
            return StatusCode.WRONG_PHASE;
        }
        calculatePhase2(g);
        g.turn.nextPhase();
        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 3">
    public StatusCode endPhase3(Long game_id) throws Exception
    {
        Game g = GameWorld.getGame(game_id);
        if(g == null)
        {
            return StatusCode.NO_SUCH_GAME;
        }
        if(g.turn.phase != 3)
        {
            return StatusCode.WRONG_PHASE;
        }
        calculatePhase3(g);
        g.turn.nextPhase();
        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    //<editor-fold desc="Calculations">

    //<editor-fold desc="Phase 0">
    private void calculateIndustry(Game g)
    {
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);

            g.industry_performance.industry_profit_after_taxes.add(company.company_state.net_profit.get());
            g.industry_performance.total_revenue.add(company.company_state.revenue.get());
            g.industry_performance.sell_price.set(g.product_price_and_production.p);
            g.industry_performance.industry_profit_change.add(company.company_state.profit_change.get());
            g.industry_performance.industry_costs_change.add(company.company_state.costs_change.get());
            g.industry_performance.taxes.add(company.company_state.profit_tax.get());
            g.industry_performance.total_power.add(company.products_and_capacity.power.get());
            g.industry_performance.total_production.add(company.products_and_capacity.production.get());
            g.industry_performance.power_reserve.add(company.products_and_capacity.power_reserve.get() * 100);
            g.industry_performance.total_net_book_value_of_fixed_assets.add(company.company_state.net_fixed_assets.get());
            g.industry_performance.accounts_balance.add(company.company_state.cash.get());

        }
        g.industry_performance.capacity_unit_price.set(g.industry_performance.total_net_book_value_of_fixed_assets.get() / g.industry_performance.total_power.get());

    }
    private void calculateStep0(Game g) throws Exception
    {
        //<editor-fold desc="Мощности и производство">
        Double totalPowerSensor = 0.0;
        Double totalProductionSensor = 0.0;
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            company.products_and_capacity.power_sensor.set(Math.random(g.startSettings.power_coefficient_min.get(),g.startSettings.power_coefficient_max.get(),3));
            totalPowerSensor+=company.products_and_capacity.power_sensor.get();
        }
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            company.products_and_capacity.power.set(company.products_and_capacity.power_sensor.get()
                    /totalPowerSensor
                    *g.startSettings.total_capacity.get());
            company.products_and_capacity.production_sensor.set(Math.random(g.startSettings.power_loading_coefficient_min.get(),g.startSettings.power_loading_coefficient_max.get(),3));
            totalProductionSensor+=company.products_and_capacity.production_sensor.get();
            company.products_and_capacity.production.set(company.products_and_capacity.power.get()*company.products_and_capacity.production_sensor.get());
            company.products_and_capacity.power_reserve.set(company.products_and_capacity.power.get()-company.products_and_capacity.production.get());
        }
        //</editor-fold>

        //<editor-fold desc="Расчет «Цены единицы продукции в 0 цикл» и Суммарного объема Производства (P0, Q0)">
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            g.product_price_and_production.q += company.products_and_capacity.production.get();
        }

        g.product_price_and_production.p = g.startSettings.start_economics_sector_size.get()/g.product_price_and_production.q;
        g.product_price_and_production.p0 = g.product_price_and_production.p;
        //</editor-fold>

        //<editor-fold desc="Начальное состояние предприятий (Таблицы 4 companyState, 5 fixedAssetsAndDepreciation)">
        // Поправочные коэффициенты
        Double mainAssetsSensorSum = 0.0;
        Double cashSensorSum = 0.0;
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            company.company_sensors.cash_sensor.set(Math.random(0.100,0.999));
            company.company_sensors.main_assets_sensor.set(Math.random(g.startSettings.value_assets_for_all_companies_coefficient_min.get(),g.startSettings.value_assets_for_all_companies_coefficient_max.get()));
            company.company_sensors.variable_costs_sensor.set(Math.random(g.startSettings.variable_costs_as_a_percentage_of_revenue_coefficient_min.get(),g.startSettings.variable_costs_as_a_percentage_of_revenue_coefficient_max.get()));
            company.company_sensors.constant_costs_sensor.set(Math.random(g.startSettings.constant_costs_as_a_percentage_of_revenue_coefficient_min.get(),g.startSettings.constant_costs_as_a_percentage_of_revenue_coefficient_max.get()));
            mainAssetsSensorSum+=company.company_sensors.main_assets_sensor.get();
            cashSensorSum+=company.company_sensors.cash_sensor.get();
        }
        // Сами показатели предприятий
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);

            // Table 5

            // TODO: 1
            FixedAssetsAndDepreciation assets = new FixedAssetsAndDepreciation(g.startSettings.value_assets_for_all_companies.get()*company.company_sensors.main_assets_sensor.get()/mainAssetsSensorSum
                    ,g.startSettings.total_depreciation.get().intValue(),0,AcquisitionMethod.START);

            // Table 4
            company.company_state.revenue.set(company.products_and_capacity.production.get()*g.product_price_and_production.p);
            company.company_state.variable_costs.set(company.company_state.revenue.get()*company.company_sensors.variable_costs_sensor.get());
            company.company_state.operation_profit.set(company.company_state.revenue.get() - company.company_state.variable_costs.get());
            company.company_state.constant_costs.set(company.company_state.revenue.get()*company.company_sensors.constant_costs_sensor.get());
            company.company_state.depreciation.set(company.getDepreciation(0));
            company.company_state.percent_payment.set(0.0);
            company.company_state.profit_before_taxes.set(company.company_state.revenue.get()
                    -company.company_state.variable_costs.get()
                    -company.company_state.constant_costs.get()
                    -company.company_state.depreciation.get());
            company.company_state.profit_tax.set(company.company_state.profit_before_taxes.get()*g.startSettings.profit_tax.get());
            company.company_state.net_profit.set(company.company_state.profit_before_taxes.get()-company.company_state.profit_tax.get());
            company.company_state.profit_change.set(0.0);
            company.company_state.costs_change.set(0.0);
            company.company_state.cash.set((g.startSettings.balance_for_all_companies.get()
                    *company.company_sensors.cash_sensor.get()
                    /cashSensorSum)
                    +company.company_state.net_profit.get()
                    +company.company_state.depreciation.get());
            company.company_state.net_fixed_assets.set(company.getFixedAssetsForTurn(0)-company.getDepreciation(0));
            company.company_state.current_passives.set(0.0);
            company.company_state.debt.set(0.0);
            company.company_state.credit_value.set(0.0);
            company.company_state.share_capital.set(company.company_state.cash.get()+company.company_state.net_fixed_assets.get());
            company.company_state.retained_earnings.set(company.company_state.net_profit.get());
            company.company_state.ebitda.set(company.company_state.revenue.get()-company.company_state.constant_costs.get()-company.company_state.variable_costs.get());
            company.company_state.noplat.set(company.company_state.net_profit.get()-company.company_state.depreciation.get());
            company.company_state.investments.set(0.0);
            company.company_state.variable_costs_coefficient.set(company.company_sensors.variable_costs_sensor.get());
            company.company_state.constant_costs_coefficient.set(company.company_sensors.constant_costs_sensor.get());
            company.company_state.market_share.set(company.products_and_capacity.production_sensor.get()/totalProductionSensor*100.0);
        }
        // Стоимость основных средств и амортизация
        //</editor-fold>

        //<editor-fold desc="Показатели отрасли (Таблица 8)">
        calculateIndustry(g);
        //</editor-fold>

        //<editor-fold desc="Расчет кривой спроса">
        calculateDemandCurve(g);
        //</editor-fold>

        //<editor-fold desc="Бизнес план">
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            BusinessPlanItem item = new BusinessPlanItem();
            for(int turn = 1; turn<30; turn++)
            {
                item = (BusinessPlanItem)Helper.clone(item);
                item.turn = turn;
                item.revenue = g.product_price_and_production.p*company.products_and_capacity.production.get();
                item.variable_costs = item.revenue*company.company_sensors.variable_costs_sensor.get();
                item.operation_profit = item.revenue-item.variable_costs;
                item.constant_costs = company.company_state.constant_costs.get();
                // Depreciation
                // TODO: 2
                if(company.fixed_assets_and_depreciation.get(0).acquisition_method== AcquisitionMethod.START &&turn<company.fixed_assets_and_depreciation.get(0).depreciation_period-1)
                {
                    item.depreciation = company.fixed_assets_and_depreciation.get(0).depreciation.get();
                }
                else if(company.fixed_assets_and_depreciation.get(0).acquisition_method == AcquisitionMethod.START &&turn==company.fixed_assets_and_depreciation.get(0).depreciation_period-1)
                {
                    item.depreciation = company.fixed_assets_and_depreciation.get(0).fixed_assets_cost.get()
                            - (company.fixed_assets_and_depreciation.get(0).depreciation.get()
                            * (company.fixed_assets_and_depreciation.get(0).depreciation_period-1));
                }
                else
                {
                    item.depreciation = 0.0;
                }
                item.interest_payment = 0.0;
                item.profit_before_taxes = item.revenue - item.variable_costs - item.constant_costs - item.depreciation;
                item.profit_taxes = item.profit_before_taxes<0 ? 0.0 : item.profit_before_taxes*g.startSettings.profit_tax.get();
                item.net_profit = item.profit_before_taxes - item.profit_taxes;
                item.cash = turn==1 ? company.company_state.cash.get() + item.net_profit + item.depreciation : item.cash + item.net_profit + item.depreciation;
                item.net_fixed_assets = (company.fixed_assets_and_depreciation.get(0).acquisition_method== AcquisitionMethod.START && turn<(company.fixed_assets_and_depreciation.get(0).depreciation_period-1))
                        ? company.fixed_assets_and_depreciation.get(0).fixed_assets_cost.get()-company.fixed_assets_and_depreciation.get(0).depreciation.get()*(turn+1) :0.0;
                item.current_liabilities = 0.0;
                item.debt = 0.0;
                item.credit_size = 0.0;
                item.retained_earnings = (turn == 1) ? company.company_state.retained_earnings.get() + item.net_profit : item.retained_earnings+item.net_profit;
                item.share_capital = item.cash + item.net_fixed_assets - item.retained_earnings;
                item.ebitda = item.revenue - item.variable_costs - item.constant_costs;
                item.noplat = item.net_profit + item.depreciation;
                item.investments = 0.0;
                item.variable_costs_factor = company.company_sensors.variable_costs_sensor.get();
                item.constant_costs_factor = company.company_sensors.constant_costs_sensor.get();
                item.market_share = item.revenue/g.industry_performance.total_revenue.get()*100.0;
                company.business_plan.items.add(item);
            }
        }
        //</editor-fold>
    }
    private void calculateDemandCurve(Game g)
    {
        //<editor-fold desc="Шаг цены">
        Double P = g.product_price_and_production.p;
        Double Q = g.product_price_and_production.q;
        Double delta = P/5.0;
        if(P>=1000)
        {
            int quant = (int)(delta/10.0);
            delta = 1.0*quant*10;
        }
        if(P>=25&&P<1000)
        {
            int quant = (int)(delta/5.0);
            delta = 1.0*quant*5;
        }
        if(P>=10&&P<25)
        {
            int quant = (int)(delta/2.0);
            delta = 1.0*quant*2;
        }
        if(P<10)
        {
            delta = (double)delta.intValue();
        }
        g.product_price_and_production.delta_price = delta;
        //</editor-fold>

        //<editor-fold desc="Максимальное значение цены">
        Double maxPrice = 0.0;
        if(g.startSettings.elasticity_function_type == ElasticityFunctionType.LINEAR)
        {
            maxPrice = P+(P*Q)/(g.startSettings.elasticity_decrease.get()*Q);
            Integer quantifier = (int)(maxPrice/delta);
            maxPrice = 1.0*quantifier*delta;
        }
        if(g.startSettings.elasticity_function_type == ElasticityFunctionType.CONSTANT)
        {
            Double val_3 = 0.0;
            if(P>=1000)
            {
                int quant = (int)(P/10.0);
                val_3 = 1.0*quant*10;
            }
            if(P>=25&&P<1000)
            {
                int quant = (int)(P/5.0);
                val_3 = 1.0*quant*5;
            }
            if(P>=10&&P<25)
            {
                int quant = (int)(P/2.0);
                val_3 = 1.0*quant*2;
            }
            if(P<10)
            {
                val_3 = (double)val_3.intValue();
            }
            Double val_4 = val_3/delta;
            maxPrice = val_3+delta*(val_4+3);
        }
        g.product_price_and_production.max_price = maxPrice;
        //</editor-fold>

        //<editor-fold desc="Вычисляем функцию для массива">
        DemandSupplyCurve demandSupplyCurve = new DemandSupplyCurve();
        Double currPrice = delta;
        while(currPrice<=maxPrice)
        {
            Double demand = 0.0;
            if(g.startSettings.elasticity_function_type == ElasticityFunctionType.LINEAR)
            {
                demand = Q+g.startSettings.elasticity_decrease.get()*Q*(P-currPrice)/P;
            }
            if(g.startSettings.elasticity_function_type == ElasticityFunctionType.CONSTANT)
            {
                demand = Q+ java.lang.Math.pow((currPrice / P),g.startSettings.elasticity_decrease.get());
            }
            DemandSupplyCurveItem item = new DemandSupplyCurveItem(currPrice,demand,0.0);
            demandSupplyCurve.items.add(item);
            currPrice+=delta;
        }
        g.demand_supply_curve = demandSupplyCurve;
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="Phase 1">
    private void calculateCompanyPrice(Company company,Game g)
    {
        company.company_state.company_minimum_price_with_bankrupt.set(
                (0.6-(g.turn.turn-company.company_state.bankrupt_turn)/10.0)
                        *company.company_state.net_fixed_assets.get()
                        -company.company_state.cash.get());
        company.company_state.bankrupt = company.company_state.cash.get()<=0;
        company.company_state.company_minimum_price_with_card.set(company.company_state.ebitda.get()*3
                +company.company_state.cash.get()
                -company.company_state.debt.get());
        company.company_state.company_minimum_price.set(company.company_state.cash.get()
                -company.company_state.debt.get());
    }
    private void calculatePhase1(Game g)
    {
        //<editor-fold desc="Demand supply curve">

        Double qCurrent = g.product_price_and_production.q
                +(g.startSettings.elasticity_influence_coefficient.get()
                *g.industry_performance.industry_profit_after_taxes.get()
                /g.product_price_and_production.p0)
                *(1+g.tradeFactors.magnification_demand.get());

        Double currMinPrice = 0.0;
        g.demand_supply_curve.items.clear();
        g.demand_supply_curve.satisfying_price_supply = null;
        g.demand_supply_curve.current_sell_price = 0.0;
        Double volume = 0.0;
        for(int i=0; i<g.companies.size(); i++)
        {
            Double currPrice = Double.MAX_VALUE;
            boolean gotNew = false;
            for(int j=0; j<g.companies.size(); j++)
            {
                Double companyPrice = g.companies.get(i).goodsDeclaration.price.get();
                Double companyVolume = g.companies.get(i).goodsDeclaration.value.get();
                if(companyPrice>=currMinPrice)
                {
                    if(companyPrice<currPrice)
                    {
                        currPrice=companyPrice;
                        volume+=companyVolume;
                        gotNew = true;
                    }
                    else if(companyPrice==currPrice)
                    {
                        volume+=companyVolume;
                        gotNew = true;
                    }
                }
            }
            currMinPrice = currPrice;
            // Here we get next price and volume
            if(gotNew)
            {
                Double stepPrice = currPrice.doubleValue();
                Double stepVolume = volume.doubleValue();
                Double qForPrice = 0.0;
                if(g.startSettings.elasticity_function_type == ElasticityFunctionType.LINEAR)
                {
                    qForPrice = qCurrent+g.startSettings.elasticity_decrease.get()*qCurrent*(g.product_price_and_production.p0-stepPrice)/g.product_price_and_production.p0;
                }
                else
                {
                    //QТЦ*(Цена/ P0)^ «Табл. 1_ Уменьшение эластичности»
                    qForPrice = java.lang.Math.pow(qCurrent*(stepPrice/g.product_price_and_production.p0),g.startSettings.elasticity_decrease.get());
                }
                if(stepVolume>=qForPrice)// We have a winner
                {
                    if(g.demand_supply_curve.satisfying_price_supply==null)
                    {
                        g.demand_supply_curve.satisfying_price_supply = new DemandSupplyCurveItem(stepPrice,qForPrice,stepVolume);
                        g.demand_supply_curve.current_sell_price = stepPrice;
                    }
                }
                g.demand_supply_curve.items.add(new DemandSupplyCurveItem(stepPrice,qForPrice,stepVolume));
            }
        }
        //</editor-fold>

        //<editor-fold desc="Company state">
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);

            company.company_state.revenue.set(0.0);
            if(company.goodsDeclaration.price.get()<=g.demand_supply_curve.current_sell_price)
            {
                company.company_state.revenue.set(company.goodsDeclaration.value.get()*g.demand_supply_curve.current_sell_price);
            }
            g.industry_performance.total_revenue.set(g.industry_performance.total_revenue.get()+company.company_state.revenue.get());
            Double lastVariableCosts = company.company_state.variable_costs.get().doubleValue();
            company.company_state.variable_costs.set(company.company_state.revenue.get()*company.company_sensors.variable_costs_sensor.get());
            Double lastOperationalProfit = company.company_state.operation_profit.get().doubleValue();
            company.company_state.operation_profit.set(company.company_state.revenue.get() - company.company_state.variable_costs.get());
            // add cards
            Double lastConstantCosts = company.company_state.constant_costs.get().doubleValue();
            Double currentConstantCosts = company.company_state.revenue.get()*company.company_sensors.constant_costs_sensor.get();
            if(company.company_state.sell_turn==g.turn.turn-1)
            {
                currentConstantCosts = company.company_state.sell_price*company.company_sensors.constant_costs_sensor.get();
            }
            company.company_state.constant_costs.set(company.company_state.revenue.get()*company.company_sensors.constant_costs_sensor.get());
            company.company_state.profit_before_taxes.set(company.company_state.revenue.get()
                    -company.company_state.variable_costs.get()
                    -company.company_state.constant_costs.get()
                    -company.company_state.depreciation.get());
            company.company_state.profit_tax.set(company.company_state.profit_before_taxes.get()*g.startSettings.profit_tax.get());
            company.company_state.net_profit.set(company.company_state.profit_before_taxes.get()-company.company_state.profit_tax.get());
            company.company_state.profit_change.set(company.company_state.operation_profit.get()-lastOperationalProfit);
            company.company_state.costs_change.set(company.company_state.variable_costs.get()+company.company_state.constant_costs.get()
                    -lastConstantCosts-lastVariableCosts);
            Double lastDebt = company.company_state.debt.get();
            company.company_state.cash.set(company.company_state.cash.get()+company.company_state.net_profit.get()
                    +company.company_state.depreciation.get()
                    -lastDebt
                    -company.company_state.debt.get());
            company.company_state.ebitda.set(company.company_state.revenue.get()-company.company_state.constant_costs.get()-company.company_state.variable_costs.get());
            company.company_state.noplat.set(company.company_state.net_profit.get()-company.company_state.depreciation.get());
            company.company_state.investments.set(0.0);
            Double percentPaid = company.company_state.percent_payment.get();
            if(percentPaid == 0.0)
            {
                company.company_state.setCredit_rating_by_edibta(CreditRating.AAA);
            }
            else if(company.company_state.ebitda.get()/percentPaid>=10)
            {
                company.company_state.setCredit_rating_by_edibta(CreditRating.AAA);
            }
            else if(company.company_state.ebitda.get()/percentPaid>=6)
            {
                company.company_state.setCredit_rating_by_edibta(CreditRating.BBB);
            }
            else if(company.company_state.ebitda.get()/percentPaid>=3)
            {
                company.company_state.setCredit_rating_by_edibta(CreditRating.CCC);
            }
            else
            {
                company.company_state.setCredit_rating_by_edibta(CreditRating.DDD);
            }
            Double cash = company.company_state.cash.get();

            if(cash==0.0)
            {
                company.company_state.setCredit_rating_by_cash(CreditRating.DDD);
            }
            else if((cash-company.company_state.current_passives.get())/cash>0.7)
            {
                company.company_state.setCredit_rating_by_cash(CreditRating.AAA);
            }
            else if((cash-company.company_state.current_passives.get())/cash>0.4)
            {
                company.company_state.setCredit_rating_by_cash(CreditRating.BBB);
            }
            else if((cash-company.company_state.current_passives.get())/cash>0.1)
            {
                company.company_state.setCredit_rating_by_cash(CreditRating.CCC);
            }
            company.company_state.power.set(company.company_state.power.get()+company.company_state.new_power.get());
            company.company_state.production_loading.set(company.company_state.sales_volume.get()/company.company_state.power.get()*100);
            calculateCompanyPrice(company,g);
            company.company_state.market_share.set(company.company_state.revenue.get()/g.industry_performance.total_revenue.get()*100.0);
            company.company_state.free_power.set(100.0-company.company_state.production_loading.get());
            if(g.demand_supply_curve.current_sell_price<=company.goodsDeclaration.price.get()||company.event_card_contract_entire_volume !=null)
            {
                company.company_state.sales_volume.set(company.goodsDeclaration.value.get());
            }
            else
            {
                company.company_state.sales_volume.set(0.0);
            }
            // Variable and constant sensors
            if(g.turn.turn<3)
            {
                company.company_state.variable_costs_coefficient.set(company.company_sensors.variable_costs_sensor.get());
                company.company_state.constant_costs_coefficient.set(company.company_sensors.constant_costs_sensor.get());
            }
            else if(company.event_card_efficiency_project_small.pre_previous_turn)
            {
                Double val = company.company_state.variable_costs_coefficient.get()*(1-g.startSettings.variable_costs_decrease_coefficient_small_project.get());
                company.company_state.variable_costs_coefficient.set(val);
            }
            else if(company.event_card_efficiency_project_large.pre_previous_turn)
            {
                Double val = company.company_state.variable_costs_coefficient.get()*(1-g.startSettings.variable_costs_decrease_coefficient_big_project.get());
                company.company_state.variable_costs_coefficient.set(val);
            }
            else
            {
                company.company_state.variable_costs_coefficient.set(company.company_state.variable_costs_coefficient.get());
            }

            if(g.turn.turn<3)
            {
                company.company_state.constant_costs_coefficient.set(company.company_sensors.constant_costs_sensor.get());
            }
            else if(company.event_card_efficiency_project_medium.pre_previous_turn)
            {
                Double val = company.company_state.constant_costs_coefficient.get()*(1-g.startSettings.constant_costs_decrease_coefficient_medium_project.get());
                company.company_state.constant_costs_coefficient.set(val);
            }
            else
            {
                company.company_state.constant_costs_coefficient.set(company.company_state.constant_costs_coefficient.get());
            }
            company.company_state.retained_earnings.set(company.company_state.retained_earnings.get()+company.company_state.net_profit.get());
            company.company_state.bankrupt = company.isBankupt();
            if(company.company_state.bankrupt)
            {
                sell(company,g,CompanySellReason.BANKRUPT);
            }
        }
        //</editor-fold>


    }
    //</editor-fold>

    //<editor-fold desc="Phase 2">
    private void calculatePhase2(Game g)
    {
        //<editor-fold desc="Перераспределение предприятий">
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            if(company.sell_parameters.on_sale)
            {
                CompanyBet winnerBet = null;
                Iterator<Long> playerIter = company.company_bets.keySet().iterator();
                while(playerIter.hasNext())
                {
                    Long id = playerIter.next();
                    CompanyBet bet = company.company_bets.get(id);
                    if(winnerBet==null||winnerBet.value<bet.value)
                    {
                        winnerBet = bet;
                    }
                    else if(winnerBet.value==bet.value&&winnerBet.time>bet.time)
                    {
                        winnerBet = bet;
                    }
                }
                if(winnerBet!=null)
                {
                    companySold(g,company,g.getCompany(winnerBet.company_id),winnerBet);
                }
            }
        }
        //</editor-fold>
        calculateIndustry(g);
        //<editor-fold desc="Calculate PPE prices">
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            company.event_card_efficiency_project_small.event_card_price = 0.0;
            company.event_card_efficiency_project_medium.event_card_price = g.startSettings.investment_constant_costs_coefficient_medium_project.get()
                    *company.company_state.constant_costs.get();
            company.event_card_efficiency_project_large.event_card_price = g.startSettings.current_power_percent_big_project.get()
                    *(company.company_state.power.get()+company.company_state.new_power.get())
                    *(1+g.startSettings.exceeds_the_net_book_value_of_fixed_assets_coefficient_big_project.get())
                    *g.industry_performance.capacity_unit_price.get();
            company.company_state.new_power.set(g.startSettings.current_power_percent_big_project.get()
                    *(company.company_state.power.get()+company.company_state.new_power.get()));

            Double canSpend = 0.0;
            if(company.company_state.credit_rating_current!=CreditRating.DDD)
            {
                canSpend = Double.MAX_VALUE;
            }
            else
            {
                canSpend = company.company_state.cash.get();
            }

            company.event_card_efficiency_project_small.can_accept = false;
            company.event_card_efficiency_project_medium.can_accept = false;
            company.event_card_efficiency_project_large.can_accept = false;

            if(canSpend>company.event_card_efficiency_project_small.event_card_price)
            {
                company.event_card_efficiency_project_small.can_accept = true;
            }
            if(canSpend>company.event_card_efficiency_project_medium.event_card_price)
            {
                company.event_card_efficiency_project_medium.can_accept = true;
            }
            if(canSpend>company.event_card_efficiency_project_large.event_card_price)
            {
                company.event_card_efficiency_project_large.can_accept = true;
            }

            // Contract entire volume
            company.event_card_contract_entire_volume.event_card_value = g.product_price_and_production.p*0.95;
        }
        //</editor-fold>
    }
    private void companySold(Game g, Company seller_company,Company buyer_company,CompanyBet bet)
    {
        //<editor-fold desc="Передать другому игроку">
        Iterator<Player> plIter = g.players.values().iterator();
        Company sold = null;
        Player buyer = null;
        Player seller = null;
        while(plIter.hasNext())
        {
            Player pl = plIter.next();
            if(pl.getCompany(buyer_company.id)!=null)
            {
                buyer = pl;
            }
            if(pl.getCompany(seller_company.id)!=null)
            {
                seller = pl;
            }
            if(pl.getCompany(seller_company.id)!=null)// Sold found
            {
                sold = pl.getCompany(seller_company.id);
                pl.removeCompany(sold.id);
            }
        }
        buyer.companies.add(sold);
        //</editor-fold>

        //<editor-fold desc="Рассчитать новые показатели предприятия покупателя">
        if(bet.credit!=null)
        {
            buyer_company.company_credits.add(bet.credit);
            buyer_company.company_state.cash.add(bet.credit.credit_value);
        }
        buyer_company.company_state.cash.add(-bet.value);
        buyer_company.company_state.share_capital.add(-bet.value);
        buyer_company.company_state.investments.add(bet.value);
        //</editor-fold>

        //<editor-fold desc="Рассчитать новые показатели проданного предприятия">
        seller.cash += seller_company.company_state.cash.get()+bet.value;
        if(seller_company.isBankupt())
        {
            seller_company.company_state.net_fixed_assets.set(bet.value);
            seller_company.company_state.current_passives.set(0.0);
            seller_company.company_state.debt.set(0.0);
            seller_company.company_credits.clear();
            seller_company.company_state.share_capital.set(seller_company.company_state.net_fixed_assets.get());
            seller_company.company_state.retained_earnings.set(0.0);
            // TODO: 3
            seller_company.fixed_assets_and_depreciation.get(0).depreciation_period = g.startSettings.total_depreciation.get().intValue();
            seller_company.fixed_assets_and_depreciation.get(0).depreciation.set(seller_company.company_state.net_fixed_assets.get()
                    /seller_company.fixed_assets_and_depreciation.get(0).depreciation_period);
            seller_company.fixed_assets_and_depreciation.get(0).acquisition_method = AcquisitionMethod.BUY_BANKRUPT;
            seller_company.company_state.bankrupt = false;
        }

        //</editor-fold>
    }
    private StatusCode acceptCard(Company company,String card,String size,Game g,Credit credit)
    {
//        CONTRACT_ENTIRE_VOLUME("Законтрактовать весь объем")
//                , SELL_COMPANY("Продажа предприятия")
//                , EFFICIENCY_PROJECT_SMALL("Проект повышения эффективности 1")
//                , EFFICIENCY_PROJECT_MEDIUM("Проект повышения эффективности 2")
//                , EFFICIENCY_PROJECT_LARGE("Проект повышения эффективности 3")
//                , POCKING("Поккинг");
        if(card.equalsIgnoreCase("CONTRACT_ENTIRE_VOLUME"))
        {
            if(company.event_card_contract_entire_volume.current_turn)
            {
                company.event_card_contract_entire_volume.accepted = true;
            }
        }
        if(card.equalsIgnoreCase("SELL_COMPANY"))
        {
            if(company.event_card_sell_company.current_turn)
            {
                company.event_card_sell_company.accepted = true;
                sell(company, g, CompanySellReason.EVENT_CARD);
            }
        }
        if(card.equalsIgnoreCase("EFFICIENCY_PROJECT_SMALL"))
        {
            if((company.event_card_efficiency_project_small.current_turn
                    ||company.event_card_efficiency_project_medium.current_turn
                    ||company.event_card_efficiency_project_large.current_turn)
                &&company.event_card_efficiency_project_small.can_accept)
            {
                company.event_card_efficiency_project_small.accepted = true;
                return ppe(company,g,EventCardType.EFFICIENCY_PROJECT_SMALL,credit);
            }
            else
            {
                return StatusCode.CANT_ACCEPT_CARD;
            }
        }
        if(card.equalsIgnoreCase("EFFICIENCY_PROJECT_MEDIUM"))
        {
            if((company.event_card_efficiency_project_medium.current_turn
                    ||company.event_card_efficiency_project_large.current_turn)
                    &&company.event_card_efficiency_project_medium.can_accept)
            {
                company.event_card_efficiency_project_medium.accepted = true;
                return ppe(company,g,EventCardType.EFFICIENCY_PROJECT_MEDIUM,credit);
            }
            else
            {
                return StatusCode.CANT_ACCEPT_CARD;
            }
        }
        if(card.equalsIgnoreCase("EFFICIENCY_PROJECT_SMALL"))
        {
            if((company.event_card_efficiency_project_large.current_turn)
                    &&company.event_card_efficiency_project_large.can_accept)
            {
                company.event_card_efficiency_project_large.accepted = true;
                return ppe(company,g,EventCardType.EFFICIENCY_PROJECT_LARGE,credit);
            }
            else
            {
                return StatusCode.CANT_ACCEPT_CARD;
            }
        }
        if(card.equalsIgnoreCase("POCKING"))
        {
            if(company.event_card_pocking.current_turn)
            {
                company.event_card_pocking.accepted = true;
                pocking(company,g,size);
            }
        }

        return StatusCode.SUCCESS;
    }
    private StatusCode ppe(Company c,Game g,EventCardType type,Credit credit)
    {
        calculateIndustry(g);
        return StatusCode.SUCCESS;
    }
    private void pocking(Company c,Game g,String size)
    {
        calculateIndustry(g);
    }
    private void sell(Company c,Game g,CompanySellReason reason)
    {
        //Check can sale
        c.sell_parameters.on_sale = true;

        // Calculate sell price
        calculateCompanyPrice(c,g);
        if(reason == CompanySellReason.FREE_WILL)
        {
            c.sell_parameters.sell_reason = CompanySellReason.FREE_WILL;
            c.sell_parameters.on_sale = true;
            c.sell_parameters.sell_price = c.company_state.company_minimum_price.get();
        }
        else if(reason == CompanySellReason.BANKRUPT)
        {
            c.sell_parameters.sell_reason = CompanySellReason.BANKRUPT;
            c.sell_parameters.on_sale = true;
            c.sell_parameters.sell_price = c.company_state.company_minimum_price_with_bankrupt.get();
        }
        else if(reason == CompanySellReason.EVENT_CARD)
        {
            c.sell_parameters.sell_reason = CompanySellReason.EVENT_CARD;
            c.sell_parameters.on_sale = true;
            c.sell_parameters.sell_price = c.company_state.company_minimum_price_with_card.get();
        }
    }
    private StatusCode bet(Company c,Player p,CompanyBet bet, Game g)
    {
        Long companyId = bet.company_id;
        Company company = g.getCompany(companyId);
        if(company.company_state.credit_rating_current!=CreditRating.DDD)
        {
            if(bet.credit.credit_term.intValue()==3)
            {
                bet.credit.rate_on_the_loan = company.company_state.credit_rate_of_interest_3_years.get();
            }
            else if(bet.credit.credit_term.intValue()==5)
            {
                bet.credit.rate_on_the_loan = company.company_state.credit_rate_of_interest_5_years.get();
            }
            else if(bet.credit.credit_term.intValue()==7)
            {
                bet.credit.rate_on_the_loan = company.company_state.credit_rate_of_interest_7_years.get();
            }
            else
            {
                return StatusCode.CANT_GET_CREDIT;
            }
            company.company_bets.put(p.id,bet);
        }
        company.company_bets.put(p.id,bet);
        return StatusCode.SUCCESS;
    }
    private StatusCode consolidation(Company c,Double value,Player p,Game g)
    {
        // Is on sale
        if(c.sell_parameters.on_sale||c.company_state.bankrupt)
        {
            return StatusCode.CONSOLIDATION_DECLINED_BANKRUPT_OR_ON_SALE;
        }
        // Money?
        else if(value>0&&c.company_state.cash.get()<value)
        {
            return StatusCode.CONSOLIDATION_DECLINED_NO_MONEY;
        }
        else if(value<0&&p.cash<java.lang.Math.abs(value))
        {
            return StatusCode.CONSOLIDATION_DECLINED_NO_MONEY;
        }

        // Calculations
        c.company_state.cash.add(-value);
        p.cash+=value;
        c.company_state.share_capital.add(-value);

        Double cash_minus_debt = c.company_state.cash.get()-c.company_state.debt.get();
        Double minimumPriceFreeWill = cash_minus_debt<0?cash_minus_debt:c.company_state.debt.get();
        c.company_state.company_minimum_price.set(minimumPriceFreeWill);

        Double sellCardCondition = 3*c.company_state.ebitda.get()+c.company_state.cash.get()-c.company_state.debt.get();
        Double minimumPriceCard = sellCardCondition<0?sellCardCondition:c.company_state.debt.get();
        c.company_state.company_minimum_price_with_card.set(minimumPriceCard);
        calculateIndustry(g);

        return StatusCode.SUCCESS;
    }
    //</editor-fold>

    //<editor-fold desc="Phase 3">
    private void calculatePhase3(Game g)
    {
        // Calculate step 0 data

        // Cards
        for(int i=0; i<g.companies.size(); i++)
        {
            Company company = g.companies.get(i);
            company.event_card_efficiency_project_small.pre_previous_turn = false;
            company.event_card_efficiency_project_small.pre_previous_turn = company.event_card_efficiency_project_small.previous_turn;
            company.event_card_efficiency_project_small.previous_turn = false;
            company.event_card_efficiency_project_small.previous_turn = company.event_card_efficiency_project_small.current_turn;
            company.event_card_efficiency_project_small.current_turn = false;

            company.event_card_efficiency_project_medium.pre_previous_turn = false;
            company.event_card_efficiency_project_medium.pre_previous_turn = company.event_card_efficiency_project_medium.previous_turn;
            company.event_card_efficiency_project_medium.previous_turn = false;
            company.event_card_efficiency_project_medium.previous_turn = company.event_card_efficiency_project_medium.current_turn;
            company.event_card_efficiency_project_medium.current_turn = false;

            company.event_card_efficiency_project_large.pre_previous_turn = false;
            company.event_card_efficiency_project_large.pre_previous_turn = company.event_card_efficiency_project_large.previous_turn;
            company.event_card_efficiency_project_large.previous_turn = false;
            company.event_card_efficiency_project_large.previous_turn = company.event_card_efficiency_project_large.current_turn;
            company.event_card_efficiency_project_large.current_turn = false;
        }
    }
    //</editor-fold>

    //</editor-fold>
}
