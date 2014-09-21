package com.sx.btccoin.action;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/*import com.colink.entity.BtcCoinTradeMarketInfo;
import com.colink.service.imp.ByteCoinService;
import com.colink.utils.DateUtil;
import com.colink.vo.bytecoin.ByteCoinTradeForListEntity;
import com.colink.vo.bytecoin.ByteCoinVo;*/
import com.opensymphony.xwork2.ModelDriven;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.entity.AnalyseCoinEntity;
import com.sx.btccoin.entity.BtcCoinTradeDepthRec;
import com.sx.btccoin.entity.BtcCoinTradeMarket;
import com.sx.btccoin.entity.BtcCoinTradeMarketInfo;
import com.sx.btccoin.entity.ByteCoinTradeForListEntity;
import com.sx.btccoin.okcoin.entity.OKCoinTrade;
import com.sx.btccoin.service.IBtcTradeService;
import com.sx.btccoin.util.analyse.OKCoinAnalyseUtil;
import com.sx.btccoin.util.sort.CompareAnalyseCoinSort;
import com.sx.btccoin.util.sort.CompareFireCoinSellSort2;
import com.sx.btccoin.vo.ByteCoinVo;
import com.sx.core.action.CoreAction;
import com.sx.rbac.entity.SysUserEntity;
import com.sx.util.DateUtil;
import com.sx.util.exception.SXException;

@Namespace(value="/back/btc")
@ParentPackage(value="coreaction")
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class BtcAction extends CoreAction implements ModelDriven<ByteCoinVo>{
	@Autowired
	private IBtcTradeService btctradebiz;
	private Log log = LogFactory.getLog(this.getClass());
	public static Integer BTCHINA_BTC_MARKET_ID = 1;
	public static Integer OK_BTC_MARKET_ID = 2;
	public static Integer FIRECOIN_BTC_MARKET_ID = 5;
	public static BigDecimal RATE = new BigDecimal(6.07);
	public static Double DIFF = 10.0;
	public static Integer CURSOR = 2;
	public static BigDecimal MAX_TRADE_QTY = new BigDecimal(1.0);
	// 考虑一下静态变量信息用静态变量存储账号余额信息
	public static AccountInfoEntity MTGOX_ACCOUNT;
	public static AccountInfoEntity BTCCHINA_ACCOUNT;
	public static AccountInfoEntity OKCOIN_ACCOUNT;
	public static AccountInfoEntity FIRECOIN_ACCOUNT;
	public static Double MIN_TRADE_QTY = 0.01;
	private ByteCoinVo bcv = new ByteCoinVo();
	
	/**
	 *获取平均价格
	 **/
	@SuppressWarnings("unchecked")
	@Action(value="getavergeprice")
	public void getAvergePrice() throws SXException{
		String str = "";
		try {
			String type = "average";
			List<AnalyseCoinEntity> list = makeAnalyseCoinEntity(type);
			Collections.sort(list, new CompareAnalyseCoinSort());
			str = JSONObject.toJSONString(list);
		} catch (Exception e) {
			log.error("获取平均价格",e);
			throw new SXException("获取平均价格!",e);
		}
		outJsonString(str);
	}
	
	
	/**
	 *获取主动买的数量
	 *(各BTC交易市场的汇总) 
	 **/
	@SuppressWarnings("unchecked")
	@Action(value="btcqtytradeforbuying")
	public void getBtcQtyTradeForBuying() throws SXException{
		List<AnalyseCoinEntity> list = new ArrayList<AnalyseCoinEntity>();
		try {
			String type = "buy";
			list = makeAnalyseCoinEntity(type);
			Collections.sort(list, new CompareAnalyseCoinSort());
		} catch (Exception e) {
			log.error("获取主动买BTC的数量",e);
			throw new SXException("获取主动买BTC的数量!",e);
		}
		outJsonString(JSONObject.toJSONString(list));
	}
	
	/**
	 *获取主动卖的数量
	 *(各BTC交易市场的汇总) 
	 **/
	@SuppressWarnings("unchecked")
	@Action(value="btcqtytradeforselling")
	public void getBtcQtyTradeForSelling() throws SXException{
		List<AnalyseCoinEntity> list = new ArrayList<AnalyseCoinEntity>();
		try {
			String type = "sell";
			list = makeAnalyseCoinEntity(type);
			Collections.sort(list, new CompareAnalyseCoinSort());
		} catch (Exception e) {
			log.error("获取主动卖BTC的数量",e);
			throw new SXException("获取主动卖BTC的数量!",e);
		}
		outJsonString(JSONObject.toJSONString(list));
	}
	/**
	 *根据主动买的type 和网站上面返回的str数据 得到一个hashmap
	 * OKCoin   type = "sell" "buy"
	 * FireCoin type = "卖出"  "买入"
	 * BTCChina type = "sell" "buy"
	 **/
	private void makeAnalyseBTCChinaBtcMapForSale(String type,HashMap<Long,AnalyseCoinEntity> map,List<?> list) throws SXException{
		
	}
	/**
	 * 根据主动买的type 和网站上面返回的str数据 得到一个hashmap
	 * OKCoin   type = "sell" "buy"
	 * FireCoin type = "卖出"  "买入"
	 * BTCChina type = "sell" "buy"
	 **/
	private void makeAnalyseOKCoinBtcMapForSale(String type,HashMap<Long,AnalyseCoinEntity> map,String str) throws SXException{
		List<OKCoinTrade> list = (List<OKCoinTrade>)JSON.parseArray(str, OKCoinTrade.class);
		boolean flag = false;
		
		if("average".equals(type)){
			for (OKCoinTrade coin : list){
				coin.setDate(Long.parseLong((coin.getDate()+"000")));
				AnalyseCoinEntity ace = new AnalyseCoinEntity();
				if(map.get(coin.getDate())==null){
					ace.setPrice(coin.getPrice());
					ace.setQty(coin.getAmount());
					ace.setDate(coin.getDate());
					map.put(coin.getDate(), ace);
				}else{
					AnalyseCoinEntity _tempace = new AnalyseCoinEntity();
					_tempace = map.get(coin.getDate());
//					System.out.println("187===>"+coin.getDate());
					BigDecimal sumprice = (_tempace.getPrice().multiply(_tempace.getQty())).add(coin.getPrice().multiply(coin.getAmount()));
					BigDecimal qty = _tempace.getQty().add(coin.getAmount());
					BigDecimal average = sumprice.divide(qty,2,BigDecimal.ROUND_HALF_UP);
					_tempace.setPrice(average);
					_tempace.setQty(qty);
					//赋值
					map.get(coin.getDate()).setDate(_tempace.getDate());
					map.get(coin.getDate()).setPrice(_tempace.getPrice());
					map.get(coin.getDate()).setQty(_tempace.getQty());
				}
			
			}
		}else{
			for (OKCoinTrade coin : list) {
				coin.setDate(Long.parseLong((coin.getDate()+"000")));
				if (type.equals(coin.getType())) {
					AnalyseCoinEntity ace = new AnalyseCoinEntity();
					ace.setDate(coin.getDate());
					ace.setPrice(coin.getPrice());
					ace.setQty(coin.getAmount());
					if(map.get(coin.getDate())==null){
						map.put(coin.getDate(), ace);
					}else{
						AnalyseCoinEntity _tempace = new AnalyseCoinEntity();
						_tempace = map.get(coin.getDate());
						_tempace.setQty(_tempace.getQty().add(ace.getQty()));
						map.get(coin.getDate()).setDate(_tempace.getDate());
						map.get(coin.getDate()).setPrice(_tempace.getPrice());
						map.get(coin.getDate()).setQty(_tempace.getQty());
					}
				}
			}
		}
	}
	/**
	 *根据主动买的type 和网站上面返回的str数据 得到一个hashmap
	 * OKCoin   type = "sell" "buy"
	 * FireCoin type = "卖出"  "买入"
	 * BTCChina type = "sell" "buy"
	 **/
	private void makeAnalyseFireCoinBtcMapForSale(String type,HashMap<Long,AnalyseCoinEntity> map,List<?> list) throws SXException{
		
	}
	
	/**
	 *根据主动买的type数量是
	 * OKCoin   type = "sell" "buy"
	 * FireCoin type = "卖出"  "买入"
	 * BTCChina type = "sell" "buy"
	 **/
	private List<AnalyseCoinEntity> makeAnalyseCoinEntity(String type) throws SXException{
		List<AnalyseCoinEntity> list = new ArrayList<AnalyseCoinEntity>();
		try {
			String okcoinstr = OKCoinAnalyseUtil.getOKCoinMarketBTCDepthEntity();
			HashMap<Long,AnalyseCoinEntity> map = new HashMap<Long,AnalyseCoinEntity>();			
			//1.将btcchina的hashmap放入到主动的map里面
//			this.makeAnalyseOKCoinBtcMapForSale(type,map,okcoinstr);
			//2.将okcoin的hashmap放入到主动买的map里面
			this.makeAnalyseOKCoinBtcMapForSale(type,map,okcoinstr);
			/*Set set = okcoinHashMap.entrySet();
			Iterator<AnalyseCoinEntity> iterator = set.iterator();
			while (iterator.hasNext()) {
				AnalyseCoinEntity elem = (AnalyseCoinEntity) iterator.next();
				if(map.get(elem.getDate())==null){
					map.put(elem.getDate(), elem);
				}else{
					//把数量加到当前的时间里面
					AnalyseCoinEntity ace = map.get(elem.getDate());
					BigDecimal bd = ace.getQty();
					ace.setQty(elem.getQty().add(bd));
					map.put(elem.getDate(), ace);
				}
			}*/
//			int a = map.size();/*System.out.println("a=====>"+a);*/
			//将map转换成为一个List
			list = this.convertMapToList(map);
//			int b = list.size();/*System.out.println("b=====>"+b);*/
		} catch (Exception e) {
			log.error("获取主动买BTC的数量",e);
			throw new SXException("获取主动买BTC的数量!",e);
		}
		return list;
	}
	
	/**
	 * 将一个Map转换成一个List的集合
	 **/
	@SuppressWarnings("unchecked")
	private List<AnalyseCoinEntity> convertMapToList(HashMap<Long,AnalyseCoinEntity> map){
		List<AnalyseCoinEntity> list = new ArrayList<AnalyseCoinEntity>();
		Iterator<?> iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	    	Map.Entry entry = (Map.Entry) iter.next(); 
	    	AnalyseCoinEntity ace = (AnalyseCoinEntity)entry.getValue();
		    list.add(ace);
	    } 
	    return list;
	}
	
	/**
	 * 根据type获取OKCoin的数据
	 **/
	private HashMap<Long,AnalyseCoinEntity> makeOKCoinAnalyseCoin(String type,String responseString) throws SXException{
		HashMap<Long,AnalyseCoinEntity> map = new HashMap<Long,AnalyseCoinEntity>();
		try {
//			String str = OKCoinAnalyseUtil.getOKCoinMarketBTCDepthEntity();
			List<OKCoinTrade> list = (List<OKCoinTrade>)JSON.parseArray(responseString, OKCoinTrade.class);
			for (OKCoinTrade coin : list) {
				coin.setDate(Long.parseLong((coin.getDate()+"000")));
				if (type.equals(coin.getType())) {
					AnalyseCoinEntity ace = new AnalyseCoinEntity();
					ace.setDate(coin.getDate());
					ace.setPrice(coin.getPrice());
					ace.setQty(coin.getAmount());
					map.put(coin.getDate(), ace);
				}
			}
		} catch (Exception e) {
			log.error("根据type获取OKCoin的数据",e);
			throw new SXException("根据type获取OKCoin的数据",e);
		}
		return map;
	}
	
	
	
	/**
	 * 开始获取交易记录信息
	 **/
	@SuppressWarnings("unchecked")
	@Action(value="addanalysisbtcpoint")
	public void addAnalysisBtcPoint() throws SXException{
		String str = "";
		try {
			String type = super.getRequest().getParameter("tradetype");
			if("1".equals(type)){
				type = "sell";
			}else if("0".equals(type)){
				type = "buy";
			}else{
				type = "average";
			}
			List<AnalyseCoinEntity> list = makeAnalyseCoinEntity(type);
			Collections.sort(list, new CompareAnalyseCoinSort());
			str = JSONObject.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取交易BTC数据出现错误",e);
			throw new SXException("获取交易BTC数据出现错误!",e);
		}
		outJsonString(str);
	}
	
	
	/**
	 * 开始获取交易记录信息
	 **/
	@Action(value="analysisbtc")
	public void analysisBtc() throws SXException{
		List<OKCoinTrade> list = null;
		try {
			String str = OKCoinAnalyseUtil.getOKCoinMarketBTCDepthEntity();
			list = (List<OKCoinTrade>)JSON.parseArray(str, OKCoinTrade.class);
			for (OKCoinTrade coin : list) {
				coin.setDate(Long.parseLong((coin.getDate()+"000")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取交易BTC数据出现错误",e);
			throw new SXException("获取交易BTC数据出现错误!",e);
		}
		outJsonString(JSONObject.toJSONString(list));
	}
	
	/**
	 * 根据OKCoin交易数据
	 * 返回一个AnalyseCoinEntity的数据
	 * @tradetype = 0 主动买
	 * @tradetype = 1 主动卖
	 **/
	public HashMap<Long,AnalyseCoinEntity> makeOKCoinAnalyseMap(List<?> list,HashMap<Long,AnalyseCoinEntity> map,String tradetype){
		for (int i = 0; i < list.size(); i++) {
			OKCoinTrade oktrade = (OKCoinTrade)list.get(i);
			if(map.get(oktrade.getDate())==null){
				AnalyseCoinEntity ace = new AnalyseCoinEntity();
				ace.setDate(oktrade.getDate());
				ace.setPrice(oktrade.getPrice());
				ace.setQty(oktrade.getAmount());
				map.put(oktrade.getDate(), ace);
			}else{
				
			}
		}
		return map;
	}
	/**
	 * 返回一个Map数组,根据传入
	 * 进来的各个市场的数据
	 * @list 为各个交易网站数据
	 * @markettype 交易网站类型 1=btcchina 2=okcoin 5=firecoin
	 * @map 返回的网站数据信息
	 **/
	public HashMap<Long,AnalyseCoinEntity> findAnalyseMap(List<?> list,Integer markettype,HashMap<Long,AnalyseCoinEntity> map){
		switch (markettype) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 5:
				
				break;
				
			default:
				
				break;
		}
		return map;
	} 
	
	
	
	/**
	 * 开始进行交易
	 **/
	@Action(value="tradebtc")
	public void tradeBtc() throws SXException{
		BtcCoinTradeMarket bctm = new BtcCoinTradeMarket();
		bctm.setStatus(0);
		String str = "";
		try {
			//1.先取出来一条数据
			List<BtcCoinTradeMarket> list = btctradebiz.findBtcCoinTradeMarkets(bctm);
			BtcCoinTradeMarket b = list.size()!=0?list.get(0):bctm;
			//2.更新数据状态为1 为正在执行
			if(b.getId()!=null&&!b.getId().equals(0)){
				bctm.setStatus(1);
				bctm.setId(b.getId());
				Integer count = btctradebiz.updateBtcCoinTradeMarketStatusById(bctm);
				if (count.intValue()>0) {
					//3.执行btc下单交易信息 返回一个bctm的string对象
					str = btctradebiz.tradeBtcCoinTrade(bctm);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("执行btc交易出现异常!",e);
			throw new SXException("执行btc交易出现异常!",e);
			
		}
		outJsonString(str);
	}
	/**
	 *设置交易参数信息
	 **/
	@Action(value="settradeparam")
	public void setTradeParam() throws SXException{
		try {
			/*System.out.println("==========================");
			System.out.println(JSONObject.toJSONString(bcv));*/
			BtcAction.RATE = new BigDecimal(bcv.getRate());
			BtcAction.DIFF = bcv.getDiff();
			BtcAction.CURSOR = bcv.getCursor();
			BtcAction.MAX_TRADE_QTY = bcv.getMaxqty();
			BtcAction.MIN_TRADE_QTY = bcv.getMinqty();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SXException("设置BTC交易参数出现错误!",e);
		}
		outJsonString(JSONObject.toJSONString(bcv));
	}
	
	/**
	 *根据tradeid和type查询数据 
	 **/
	@Action(value="gettwindepthdata")
	public void getTwinDepthData() throws SXException{
		String str = "[]";
		try {
			BtcCoinTradeDepthRec aie = new BtcCoinTradeDepthRec();
//			System.out.println("==bcv.getTradetype()"+bcv.getTradetype());
			aie.setTradeid(bcv.getPkid());
			aie.setType(bcv.getTradetype());
			System.out.println(aie.getType());
			List<BtcCoinTradeDepthRec> list  = btctradebiz.findBtcCoinTradeDepthRec(aie);
			log.debug("list.size=="+list.size());
			str = JSONObject.toJSONString(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw new SXException(e);
		}
		outJsonString(str);
	}
	/**
	 * 禁用
	 **/
	@Action(value="disablemarket")
	public void disableMarket() throws SXException{
		switch (bcv.getMarketid().intValue()) {
			case 1:
				BtcAction.BTCHINA_BTC_MARKET_ID = 0;
				break;
			case 2:
				BtcAction.OK_BTC_MARKET_ID = 0;
				break;
			case 5:
				BtcAction.FIRECOIN_BTC_MARKET_ID = 0;
				break;
			default:
				break;
		}
		outJsonString("{success:true}");
	}
	/**
	 * 启用
	 **/
	@Action(value="enablemarket")
	public void enableMarket() throws SXException{
		switch (bcv.getMarketid().intValue()) {
			case 1:
				BtcAction.BTCHINA_BTC_MARKET_ID = 1;
				break;
			case 2:
				BtcAction.OK_BTC_MARKET_ID = 2;
				break;
			case 5:
				BtcAction.FIRECOIN_BTC_MARKET_ID = 5;
				break;
			default:
				break;
		}
		outJsonString("{success:false}");
	}
	
	
	/**
	 * 开始凑交易队列
	 **/
	@Action(value="makedepth")
	public void getMakeDepthTwins() throws SXException{
		BtcCoinTradeMarketInfo bctmi = new BtcCoinTradeMarketInfo();
		String tradeid = DateUtil.makeTradeCodeId();
		try {
			ByteCoinVo bc = new ByteCoinVo();
			bc.setCursor(BtcAction.CURSOR);
			bc.setDiff(BtcAction.DIFF);
			bc.setRate(BtcAction.RATE.doubleValue());
			// 2.为这个VO类加入我们的各种参数
			// 2.1 根据下标进行获取比对的数组目前是3个网站的交易深度的数据，因此只能提取出3组数据
			ByteCoinTradeForListEntity bctfl = btctradebiz.getByteCoinTradeEntity(bc,tradeid);
			// 2.2 根据获取的两个交易数组得到返回一个可以执行的交易对
			if(bctfl!=null){
				//把获取的委买数组和委卖数组以及当时的网站的余额和可用币都存到数据库里面
				btctradebiz.addDepthRec(tradeid,bctfl);
				
				bctmi =  btctradebiz.makeBtcCoinTradeMarketInfo(bctfl,tradeid);
				
				bctmi.setStatus(1);
			}else{
				bctmi.setStatus(0);
				bctmi.setResult("网站市场没有成功返回相关的交易深度,放弃本次循环!");
			}
			bctmi.setTradeid(tradeid);
		} catch (Exception e) {
			log.error("获取交易深度数据信息!",e);
		}
		outJsonString(JSONObject.toJSONString(bctmi));
	}
	/**
	 *获取树形结构菜单数据信息 
	 **/
	@Action(value="getbtcbalance")
	public void getBtcBalance() throws SXException{
		String str = "[]";
		AccountInfoEntity aie = new AccountInfoEntity();
		aie.setStatus(1);
		List<AccountInfoEntity> list = btctradebiz.findAllAccount(aie);
		str = JSONArray.toJSONString(list);
		outJsonString(str);
	}
	/**
	 * @return the btctradebiz
	 */
	public IBtcTradeService getBtctradebiz() {
		return btctradebiz;
	}
	/**
	 * @param btctradebiz the btctradebiz to set
	 */
	public void setBtctradebiz(IBtcTradeService btctradebiz) {
		this.btctradebiz = btctradebiz;
	}
	public ByteCoinVo getModel() {
		// TODO Auto-generated method stub
		return bcv;
	}
}
