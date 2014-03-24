package racoonsoft.businesswin.service;

import org.springframework.stereotype.Service;
import racoonsoft.businesswin.structure.data.*;
import racoonsoft.businesswin.structure.enums.ElasticityFunctionType;
import racoonsoft.businesswin.structure.enums.GameMode;
import racoonsoft.businesswin.structure.model.*;
import racoonsoft.businesswin.structure.enums.GameStatus;
import racoonsoft.businesswin.structure.enums.StatusCode;
import racoonsoft.library.json.JSONProcessor;

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
    public StatusCode setTradeFactors(Long game_id, Long player_id, TradeFactors tradeFactors) throws Exception
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
    private void calculateStep0(Game g)
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
                    /totalPowerSensor //?????
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
            company.fixed_assets_and_depreciation.fixed_assets_cost.set(g.startSettings.value_assets_for_all_companies.get()
                    *company.company_sensors.main_assets_sensor.get()
                    /mainAssetsSensorSum);
            company.fixed_assets_and_depreciation.depreciation_period.set(g.startSettings.total_depreciation.get());
            company.fixed_assets_and_depreciation.depreciation.set(company.fixed_assets_and_depreciation.fixed_assets_cost.get()
                    /company.fixed_assets_and_depreciation.depreciation_period.get());
            company.fixed_assets_and_depreciation.acquisition_method = 0;

            // Table 4
            company.company_state.revenue.set(company.products_and_capacity.production.get()*g.product_price_and_production.p);
            company.company_state.variable_costs.set(company.company_state.revenue.get()*company.company_sensors.variable_costs_sensor.get());
            company.company_state.operation_profit.set(company.company_state.revenue.get() - company.company_state.variable_costs.get());
            company.company_state.constant_costs.set(company.company_state.revenue.get()*company.company_sensors.constant_costs_sensor.get());
            company.company_state.depreciation.set(company.fixed_assets_and_depreciation.depreciation.get());
            company.company_state.percent_payment.set(0.0);
            company.company_state.profit_before_taxes.set(company.company_state.revenue.get()
                    -company.company_state.variable_costs.get()
                    -company.company_state.constant_costs.get()
                    -company.company_state.depreciation.get());
            company.company_state.profit_tax.set(company.company_state.profit_before_taxes.get()*g.startSettings.profit_tax.get());
            company.company_state.net_profit.set(company.company_state.profit_before_taxes.get()-company.company_state.profit_tax.get());
            company.company_state.profit_change.set(0.0);
            company.company_state.costs_change.set(0.0);
            company.company_state.cash.set(g.startSettings.balance_for_all_companies.get()*company.company_sensors.cash_sensor.get()/cashSensorSum);
            company.company_state.net_fixed_assets.set(company.fixed_assets_and_depreciation.fixed_assets_cost.get()-company.company_state.depreciation.get());
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
            company.company_state.market_share.set(company.products_and_capacity.production_sensor.get()/totalProductionSensor/100.0);
        }
        // Стоимость основных средств и амортизация
        //</editor-fold>

        //<editor-fold desc="Показатели отрасли (Таблица 8)">
        
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

                item.turn = turn;
                item.revenue = g.product_price_and_production.p*company.products_and_capacity.production.get();
                item.variable_costs = item.revenue*company.company_sensors.variable_costs_sensor.get();
                item.operation_profit = item.revenue-item.variable_costs;
                item.constant_costs = company.company_state.constant_costs.get();
                // Depreciation
                if(company.fixed_assets_and_depreciation.acquisition_method==1&&turn<company.fixed_assets_and_depreciation.depreciation_period.get()-1)
                {
                    item.depreciation = company.fixed_assets_and_depreciation.depreciation.get();
                }
                else if(company.fixed_assets_and_depreciation.acquisition_method==1&&turn==company.fixed_assets_and_depreciation.depreciation_period.get()-1)
                {
                    item.depreciation = company.fixed_assets_and_depreciation.fixed_assets_cost.get()
                            - (company.fixed_assets_and_depreciation.depreciation.get()
                            * company.fixed_assets_and_depreciation.depreciation_period.get());
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
                item.net_fixed_assets = (company.fixed_assets_and_depreciation.acquisition_method==0 && turn<(company.fixed_assets_and_depreciation.depreciation_period.get()-1))
                        ? company.fixed_assets_and_depreciation.fixed_assets_cost.get()-company.fixed_assets_and_depreciation.depreciation.get()*(turn+1) :0.0;
                item.current_liabilities = 0.0;
                item.debt = 0.0;
                item.credit_size = 0.0;
                item.retained_earnings = (turn == 1) ? company.company_state.retained_earnings.get() + item.net_profit : item.retained_earnings+item.net_profit;
                item.share_capital = item.cash + item.net_fixed_assets + item.retained_earnings;
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
        //</editor-fold>

        //<editor-fold desc="Максимальное значение цены">
        Double maxPrice = 0.0;
        if(g.elasticity_function == ElasticityFunctionType.LINEAR)
        {
            maxPrice = P+(P*Q)/(g.startSettings.elasticity_decrease.get()*Q);
            Integer quantifier = (int)(maxPrice/delta);
            maxPrice = 1.0*quantifier*delta;
        }
        if(g.elasticity_function == ElasticityFunctionType.CONSTANT)
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
        //</editor-fold>

        //<editor-fold desc="Вычисляем функцию для массива">
        DemandCurve demandCurve = new DemandCurve();
        Double currPrice = delta;
        while(currPrice<maxPrice)
        {
            Double demand = 0.0;
            if(g.elasticity_function == ElasticityFunctionType.LINEAR)
            {
                demand = Q+g.startSettings.elasticity_decrease.get()*Q*(P-currPrice)/P;
            }
            if(g.elasticity_function == ElasticityFunctionType.CONSTANT)
            {
                demand = Q+ java.lang.Math.pow((currPrice / P),g.startSettings.elasticity_decrease.get());
            }
            DemandCurveItem item = new DemandCurveItem(currPrice,demand);
            demandCurve.items.add(item);
            currPrice+=delta;
        }
        g.demand_curve = demandCurve;
        //</editor-fold>
    }
    private void calculatePhase1(Game g)
    {
        // Calculate step 0 data
    }
    private void calculatePhase2(Game g)
    {
        // Calculate step 0 data
    }
    private void calculatePhase3(Game g)
    {
        // Calculate step 0 data
    }
    //</editor-fold>
}
