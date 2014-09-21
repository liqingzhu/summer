package com.sx.btccoin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.sx.btccoin.action.BtcAction;
import com.sx.btccoin.btcchina.entity.BtcChinaMarketDEUnit;
import com.sx.btccoin.btcchina.entity.BtcChinaMarketDepthEntity;
import com.sx.btccoin.dao.IBtcDao;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.entity.BtcCoinTradeDepthRec;
import com.sx.btccoin.entity.BtcCoinTradeMarket;
import com.sx.btccoin.entity.BtcCoinTradeMarketInfo;
import com.sx.btccoin.entity.ByteCoinTradeEntity;
import com.sx.btccoin.entity.ByteCoinTradeForListEntity;
import com.sx.btccoin.firecoin.entity.FireCoinDepth;
import com.sx.btccoin.okcoin.entity.OKCoinMarketDepthEntity;
import com.sx.btccoin.service.IBtcTradeService;
import com.sx.btccoin.util.BtcChinaUtil;
import com.sx.btccoin.util.FireCoinUtil;
import com.sx.btccoin.util.OkCoinUtil;
import com.sx.btccoin.util.sort.CompareBtcChinaBuySort;
import com.sx.btccoin.util.sort.CompareBtcChinaSellSort;
import com.sx.btccoin.util.sort.CompareBuyerSort;
import com.sx.btccoin.util.sort.CompareFireCoinBuyerSort2;
import com.sx.btccoin.util.sort.CompareFireCoinSellSort2;
import com.sx.btccoin.util.sort.CompareOKCoinBuyerSort;
import com.sx.btccoin.util.sort.CompareOKCoinSellerSort;
import com.sx.btccoin.util.sort.CompareSellerSort;
import com.sx.btccoin.vo.ByteCoinVo;
import com.sx.util.DateUtil;
@Service(value="btctradebiz")
public class BtcTradeService implements IBtcTradeService {
	@Autowired
	private IBtcDao ibtcdao;
	private Log log = LogFactory.getLog(this.getClass());
	private boolean makeSellTrade(String tradeid,Integer marketid,BigDecimal price,BigDecimal qty) {
		boolean flag = false;
		HashMap<String,Object> map = null;
		try {
			switch (marketid.intValue()) {
				case 1:
					map = BtcChinaUtil.tradeSellBtcCoin(price,qty);
					break;
				case 2:
					map = OkCoinUtil.tradeSellBtcCoin(price,qty);		
					break;
				case 5:
					map = FireCoinUtil.tradeSellBtcCoin(price,qty);
					break;
				default:
					break;
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("执行卖出出现错误!",e);
			flag = false;
		}
		return flag;
	}
	private boolean makeBuyTrade(String tradeid,Integer marketid,BigDecimal price,BigDecimal qty) throws Exception{
		boolean flag = false;
		HashMap<String,Object> map = null;
		try {
			switch (marketid.intValue()) {
				case 1:
					map = BtcChinaUtil.tradeBuyBtcCoin(price,qty);
					break;
				case 2:
					map = OkCoinUtil.tradeBuyBtcCoin(price,qty);		
					break;
				case 5:
					map = FireCoinUtil.tradeBuyBtcCoin(price,qty);
					break;
				default:
					break;
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("执行买入出现错误!",e);
			flag = false;
		}
		return flag;
	}
	/**
	 *开始交易比特币 
	 **/
	public String tradeBtcCoinTrade(BtcCoinTradeMarket bctm) throws Exception{
		String result = "";
		try {
			//开始进行买交易
			boolean buyflag = this.makeBuyTrade(bctm.getTradeid(),bctm.getBuymarketid(), bctm.getBuyprice(), bctm.getBuyqty());
			//开始进行卖交易
			boolean sellflag = this.makeSellTrade(bctm.getTradeid(),bctm.getSellmarketid(), bctm.getSellprice(), bctm.getSellqty());
			if(buyflag&&sellflag){
				System.out.println("交易成功!");
			}else if(buyflag){
				System.out.println("买入成功 卖出失败");
			}else if(sellflag){
				System.out.println("卖出成功 买入失败");
			}else{
				System.out.println("交易失败!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("开始交易比特币",e);
		}
		return result;
	}
	/**
	 * 根据id返回一条交易列表记录信息
	 **/
	public BtcCoinTradeMarket findBtcCoinTradeMarketById(String id) throws Exception{
		BtcCoinTradeMarket bctm = new BtcCoinTradeMarket();
		try {
			bctm = ibtcdao.findBtcCoinTradeMarketById(bctm);
		} catch (Exception e) {
			log.error("更新交易状态",e);
		}
		return bctm;
	}
	
	/**
	 * 根据根据id更新交易信息列表
	 **/
	public Integer updateBtcCoinTradeMarketStatusById(BtcCoinTradeMarket bctm) throws Exception{
		Integer count = new Integer(0);
		try {
			count = ibtcdao.updateBtcCoinTradeMarket(bctm);
		} catch (Exception e) {
			log.error("更新交易状态",e);
		}
		return count;
	}
	
	/**
	 * 根据状态查询交易信息返回List集合列表
	 **/
	public List<BtcCoinTradeMarket> findBtcCoinTradeMarkets(BtcCoinTradeMarket bctm) throws Exception{
		List<BtcCoinTradeMarket> list = new ArrayList<BtcCoinTradeMarket>();
		try {
			list = ibtcdao.findBtcCoinTradeMarket(bctm);
		} catch (Exception e) {
			log.error("更新交易状态",e);
		}
		return list;
	}
	/**
	 * 获取每次配对的交易队列
	 **/
	public List<BtcCoinTradeDepthRec> findBtcCoinTradeDepthRec(BtcCoinTradeDepthRec aie){
		List<BtcCoinTradeDepthRec> list = new ArrayList<BtcCoinTradeDepthRec>();
		try {
			list = ibtcdao.findBtcCoinTradeDepthRec(aie);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取每次配对的交易队列",e);
		}
		return list;
	}
	public boolean makeBalanceReduce(BtcCoinTradeMarket bctm){
		boolean flag = false;
		try {
			//sellmarket 对应的是我们的买
			Integer sellmarketid = bctm.getSellmarketid();
			/*System.out.println("sellmarket=====>"+sellmarketid);
			System.out.println(FIRECOIN_ACCOUNT.getBalance());
			System.out.println("buyprice==="+bctm.getBuyprice());
			System.out.println("tradeqty==="+bctm.getTradeqty());
			System.out.println("乘法===="+(bctm.getBuyprice().multiply(bctm.getTradeqty())));*/
			//我们需要把买入的交易金额从静态变量中减去
			switch (sellmarketid) {
				case 1:
					BtcAction.BTCCHINA_ACCOUNT.setBalance(BtcAction.BTCCHINA_ACCOUNT.getBalance().subtract(bctm.getSellprice().multiply(bctm.getTradeqty())));
					BtcAction.BTCCHINA_ACCOUNT.setBytecoinqty(BtcAction.BTCCHINA_ACCOUNT.getBytecoinqty().add(bctm.getTradeqty()));
					BtcAction.BTCCHINA_ACCOUNT.setLeftqty(BtcAction.BTCCHINA_ACCOUNT.getBytecoinqty());
					break;
				case 2:
//					System.out.println("612===>"+OKCOIN_ACCOUNT.getBalance());
					BtcAction.OKCOIN_ACCOUNT.setBalance(BtcAction.OKCOIN_ACCOUNT.getBalance().subtract(bctm.getSellprice().multiply(bctm.getTradeqty())));
//					System.out.println("614===>"+OKCOIN_ACCOUNT.getBalance());
					BtcAction.OKCOIN_ACCOUNT.setBytecoinqty(BtcAction.OKCOIN_ACCOUNT.getBytecoinqty().add(bctm.getTradeqty()));
					BtcAction.OKCOIN_ACCOUNT.setLeftqty(BtcAction.OKCOIN_ACCOUNT.getBytecoinqty());
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					BtcAction.FIRECOIN_ACCOUNT.setBalance(BtcAction.FIRECOIN_ACCOUNT.getBalance().subtract(bctm.getSellprice().multiply(bctm.getTradeqty())));
					BtcAction.FIRECOIN_ACCOUNT.setBytecoinqty(BtcAction.FIRECOIN_ACCOUNT.getBytecoinqty().add(bctm.getTradeqty()));
					BtcAction.FIRECOIN_ACCOUNT.setLeftqty(BtcAction.FIRECOIN_ACCOUNT.getBytecoinqty());
					break;
			}
			//buy对应的是我们的卖
			Integer buymarketid = bctm.getBuymarketid();
			//我们需要把卖出的币从静态变量中减去
			switch (buymarketid) {
				case 1:
					BtcAction.BTCCHINA_ACCOUNT.setBalance(BtcAction.BTCCHINA_ACCOUNT.getBalance().add(bctm.getBuyprice().multiply(bctm.getTradeqty())));
//					System.out.println("卖出时候加入后" + BTCCHINA_ACCOUNT.getBalance());
					// 交易后更新账户BTC数量
					BtcAction.BTCCHINA_ACCOUNT.setBytecoinqty(BtcAction.BTCCHINA_ACCOUNT.getBytecoinqty().subtract(bctm.getTradeqty()));
					BtcAction.BTCCHINA_ACCOUNT.setLeftqty(BtcAction.BTCCHINA_ACCOUNT.getBytecoinqty());
					break;
				case 2:
					BtcAction.OKCOIN_ACCOUNT.setBalance(BtcAction.OKCOIN_ACCOUNT.getBalance().add(bctm.getBuyprice().multiply(bctm.getTradeqty())));
					// 交易后更新账户BTC数量
					BtcAction.OKCOIN_ACCOUNT.setBytecoinqty(BtcAction.OKCOIN_ACCOUNT.getBytecoinqty().subtract(bctm.getTradeqty()));
					BtcAction.OKCOIN_ACCOUNT.setLeftqty(BtcAction.OKCOIN_ACCOUNT.getBytecoinqty());
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					BtcAction.FIRECOIN_ACCOUNT.setBalance(BtcAction.FIRECOIN_ACCOUNT.getBalance().add(bctm.getBuyprice().multiply(bctm.getTradeqty())));
					BtcAction.FIRECOIN_ACCOUNT.setBytecoinqty(BtcAction.FIRECOIN_ACCOUNT.getBytecoinqty().subtract(bctm.getTradeqty()));
					BtcAction.FIRECOIN_ACCOUNT.setLeftqty(BtcAction.FIRECOIN_ACCOUNT.getBytecoinqty());
					break;
			}
		} catch (Exception e) {
			log.error("做账户扣减工作!",e);
		}
		return flag;
	}
	/**
	 * 检查账户扣减小于零的数据信息
	 **/
	private boolean checkMinusFlag(ByteCoinTradeEntity buy,ByteCoinTradeEntity sell){
		boolean flag = false;
		try {
			
			Integer buymarketid = buy.getMarketid();
			BigDecimal buybalance = null;
			BigDecimal buyleftqty = null;
			switch (buymarketid.intValue()) {
				case 1:
					buybalance = BtcAction.BTCCHINA_ACCOUNT.getBalance();
					buyleftqty = BtcAction.BTCCHINA_ACCOUNT.getLeftbal();
					break;
				case 2:
					buybalance = BtcAction.OKCOIN_ACCOUNT.getBalance();
					buyleftqty = BtcAction.OKCOIN_ACCOUNT.getLeftbal();
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					buybalance = BtcAction.FIRECOIN_ACCOUNT.getBalance();
					buyleftqty = BtcAction.FIRECOIN_ACCOUNT.getLeftbal();
					break;
			}
			
			Integer sellmarketid = sell.getMarketid();
			BigDecimal sellbalance = null;
			BigDecimal sellleftqty = null;
			switch (sellmarketid.intValue()) {
				case 1:
					sellbalance = BtcAction.BTCCHINA_ACCOUNT.getBalance();
					sellleftqty = BtcAction.BTCCHINA_ACCOUNT.getLeftbal();
					break;
				case 2:
					sellbalance = BtcAction.OKCOIN_ACCOUNT.getBalance();
					sellleftqty = BtcAction.OKCOIN_ACCOUNT.getLeftbal();
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					sellbalance = BtcAction.FIRECOIN_ACCOUNT.getBalance();
					sellleftqty = BtcAction.FIRECOIN_ACCOUNT.getLeftqty();
					break;
			}
			if(buybalance!=null&&buybalance.doubleValue()>0&&buyleftqty!=null&&buyleftqty.doubleValue()>0
				&&sellbalance!=null&&sellbalance.doubleValue()>0&&sellleftqty!=null&&sellleftqty.doubleValue()>0){
				flag = true;
			}
		} catch (Exception e) {
			log.error("凑交易对前对余额为负的进行判断",e);
		}
		return flag;
	}
	
	/***************************************************************************
	 * shouldbuy=a shouldsell= b marketforsellbalqty=d marketforbuybalqty=c
	 **************************************************************************/
	private BigDecimal getMinQtyForTrade(BigDecimal shouldbuy,
			BigDecimal shouldsell, BigDecimal marketforsellbalqty,
			BigDecimal marketforbuybalqty) {
		BigDecimal min = null;
		try {
//			System.out.println("比较应该买的数据和应该卖出的数据 ==应该卖的数量为" + shouldbuy+ "==应该买的数量为==" + shouldsell);
			min = shouldbuy.compareTo(shouldsell) == -1 ? shouldbuy: shouldsell;
//			System.out.println("比较应该买的数据和应该卖出的数据的结果为" + min + "\n");
//			System.out.println("比较交易深度最小值和需要卖的余额 ==比较交易深度最小值为" + min+ "==需要卖的余额为==" + marketforsellbalqty);
			min = min.compareTo(marketforsellbalqty) == -1 ? min: marketforsellbalqty;
//			System.out.println("比较交易深度最小值和需要卖的余额为" + min + "\n");
//			System.out.println("最后的的数据和应该卖出的数据 ==最后的数量为" + min + "==需要买的余额是为=="+ marketforbuybalqty);
			min = min.compareTo(marketforbuybalqty) == -1 ? min: marketforbuybalqty;
//			System.out.println("最后的数据的结果为" + min + "\n");
//			System.out.println((new Date()) + "==" + "需要买的数量是==" + shouldbuy+ "  需要卖的数量是==" + shouldsell + " " + "需要卖的余额是=="+ marketforsellbalqty + "需要买的余额是==" + marketforbuybalqty);
//			System.out.println("----->最小的值为====>" + min);
//			System.out.println("----->最小的值为ROUND_FLOOR====>" + min.setScale(4,BigDecimal.ROUND_FLOOR));
		} catch (Exception e) {
			// TODO: handle exception
			log.error("交易的余额数量", e);
		}
		return min.setScale(4,BigDecimal.ROUND_FLOOR);
	}
	/**
	 *这里指判断可以交付的数量 
	 **/
	public BigDecimal getMinQty(ByteCoinTradeEntity buy,ByteCoinTradeEntity sell) throws Exception{
		BigDecimal min = null;
		try {
			// 1.开始交易之前获取Bal的数据
			AccountInfoEntity china = BtcAction.BTCCHINA_ACCOUNT;
			AccountInfoEntity okcoin = BtcAction.OKCOIN_ACCOUNT;
			AccountInfoEntity firecoin = BtcAction.FIRECOIN_ACCOUNT;
			BigDecimal marketforsellbalqty = null;
			BigDecimal marketforbuybalqty = null;
			BigDecimal marketforbuybalance = null;
			if (buy.getMarketid() == 1) {
				marketforsellbalqty = china.getLeftqty();
			} else if (buy.getMarketid() == 2) {
				marketforsellbalqty = okcoin.getLeftqty();
			} else if(buy.getMarketid()==5) {
				marketforsellbalqty = firecoin.getLeftqty();
			} else{
			}
			if (sell.getMarketid() == 1) {
				marketforbuybalance = BtcAction.BTCCHINA_ACCOUNT.getBalance();
				marketforbuybalqty = china.getLeftqty();
			} else if (sell.getMarketid() == 2) {
				marketforbuybalance = BtcAction.OKCOIN_ACCOUNT.getBalance();
				marketforbuybalqty = okcoin.getLeftqty();
			}  else if(sell.getMarketid()==5) {
				marketforbuybalqty = firecoin.getLeftqty();
				marketforbuybalance = BtcAction.FIRECOIN_ACCOUNT.getBalance();
			} else {
			}
			// 2. 获取应该买入的qty 应该卖出的qty 应该卖出的qty的balance是多少
			BigDecimal shouldsell = buy.getQty();
			BigDecimal shouldbuy = sell.getQty();
			//用需要买的账户余额除以可以买的价格，得到需要购入的比特币数量 marketforbuybalqty
			marketforbuybalqty = marketforbuybalance.divide(sell.getPrice(), 4,BigDecimal.ROUND_DOWN);
			min = getMinQtyForTrade(shouldbuy, shouldsell,marketforsellbalqty, marketforbuybalqty);
		} catch (Exception e) {
			log.error("获取可以交易的数量失败!",e);
			throw new Exception("获取可以交易的数量失败!",e);
		}
		return min;
	}
	/**
	 * 返回对应网站上面可用的钱
	 **/
	private BigDecimal getBalanceCurrenyInfo(ByteCoinTradeEntity bct) throws Exception{
		// 1.开始交易之前获取Bal的数据
		BigDecimal balance = null;
		AccountInfoEntity china = BtcAction.BTCCHINA_ACCOUNT;
		AccountInfoEntity okcoin = BtcAction.OKCOIN_ACCOUNT;
		AccountInfoEntity firecoin = BtcAction.FIRECOIN_ACCOUNT;
		if (bct.getMarketid() == 1) {
			balance = BtcAction.BTCCHINA_ACCOUNT.getBalance();
		} else if (bct.getMarketid() == 2) {
			balance = BtcAction.OKCOIN_ACCOUNT.getBalance();
		}  else if(bct.getMarketid()==5) {
			balance = BtcAction.FIRECOIN_ACCOUNT.getBalance();
		} else {
		}
		return balance;
	}
	/**
	 *返回对应网站上面返回的可用币
	 **/
	private BigDecimal getBalanceQtyInfo(ByteCoinTradeEntity bct) throws Exception{
		// 1.开始交易之前获取Bal的数据
		BigDecimal balqty = null;
		AccountInfoEntity china = BtcAction.BTCCHINA_ACCOUNT;
		AccountInfoEntity okcoin = BtcAction.OKCOIN_ACCOUNT;
		AccountInfoEntity firecoin = BtcAction.FIRECOIN_ACCOUNT;
		if (bct.getMarketid() == 1) {
			balqty = china.getLeftqty();
		} else if (bct.getMarketid() == 2) {
			balqty = okcoin.getLeftqty();
		} else if(bct.getMarketid()==5) {
			balqty = firecoin.getLeftqty();
		} else{
		}
		return balqty;
	}
	/**
	 *根据委买和委卖队列匹配对应的业务逻辑数据信息 
	 **/
	private boolean getTradeCouple(List<BtcCoinTradeMarket> list,List<ByteCoinTradeEntity> proxybuylist,List<ByteCoinTradeEntity> proxyselllist,String tradeid) throws Exception{
		   boolean flag = false;
		   for (int i = 0; i < proxybuylist.size(); i++) {
			   ByteCoinTradeEntity buy = proxybuylist.get(i);
			   ByteCoinTradeEntity sell = proxyselllist.get(i);
			   BtcCoinTradeMarket bctm = new BtcCoinTradeMarket();
			   //Buy与Sell进行 价差 对比
			   if(buy.getPrice().compareTo(sell.getPrice())==1){
				    //1.开始进行判断[buy的价格*(1-buy的市场交易手续费)-sell的价格*(1+sell的市场交易手续费)]是否大于我们自己设定的价差
					//buy的价格*(1-buy的市场交易手续费)
					BigDecimal buyd = buy.getPrice().multiply(new BigDecimal(Double.valueOf(1 - buy.getChargefee())));
					//sell的价格*(1+sell的市场交易手续费)
					/*System.out.println("====>"+(sell.getMarketname()));
					System.out.println("====>"+(Double.valueOf(1 + sell.getChargefee())));*/
					BigDecimal selld =  sell.getPrice().multiply(new BigDecimal(Double.valueOf(1 + sell.getChargefee())));
					Double diffinfo = buyd.subtract(selld).doubleValue();
					if(diffinfo>BtcAction.DIFF){
						//1.1.1符合我们的价差交易规律需要进行记录 判断buy市场的可用币是否够用
						BigDecimal btcqty = this.getBalanceQtyInfo(buy);
						if(btcqty.compareTo(new BigDecimal(0.01))==1){
							//1.1.1.1 判断Buy所对应的网站的可交易币大于 0.01
							//手里面的可用币余额大于0.01可以使用
							//继续判断sell里面的可用钱是否够买比特币的
							BigDecimal btcbalance = this.getBalanceCurrenyInfo(sell);
							BigDecimal selloneprice = (new BigDecimal(0.01)).multiply(sell.getPrice().multiply(new BigDecimal(Double.valueOf(1 + sell.getChargefee()))));
							//比较在委卖市场中的可用钱是否可以使用
							if(btcbalance.compareTo(selloneprice)==1){
								//说明可以使用
								//获得可以购买的币
								BigDecimal minqty = getMinQty(buy,sell);
								//获取网站上面允许卖出的最大额度是
								BigDecimal maxtrade = BtcAction.MAX_TRADE_QTY;
								if(minqty.compareTo(maxtrade)==1){
									minqty = maxtrade;
								}
								//加入判断余额和可用币为负数的业务逻辑
								boolean minusflag = checkMinusFlag(buy,sell);
								if((minusflag==true)&&minqty.doubleValue()>=(BtcAction.MIN_TRADE_QTY)){
									//设置可以交易的数量
									//判断如果委买的价格比委卖的价格高
									bctm.setTradeid(tradeid);
									bctm.setBuymarketid(buy.getMarketid());
									bctm.setBuyprice(buy.getPrice());
									bctm.setBuyqty(buy.getQty());
									bctm.setBuytradestatus(0);
									bctm.setSellmarketid(sell.getMarketid());
									bctm.setSellprice(sell.getPrice());
									bctm.setSellqty(sell.getQty());
									bctm.setSelltradestatus(0);
									bctm.setTradeqty(minqty);
									bctm.setCancelstatus(0);
									//设置交易状态
									bctm.setStatus(0);
									//系统中只记录凑交易对成功的信息,不成功的不记录没有用的BtcCoinTradeMarket
									Integer additem = ibtcdao.addBtcCoinTradeMarket(bctm);
									list.add(bctm);
									//获取对应的余额进行扣减
									makeBalanceReduce(bctm);
									flag = true;
									break;
								}else{
									//交易数量不够交易
									bctm.setStatus(-3);
								}
								//不管交不交易都把
//								调整proxyselllist 数组,去掉第0个值,后续的值往前挪动;	
//								 调整proxybuylist 数组,去掉第0个值,后续的值往前挪动;	
//								 递归 GetTradeCouple(proxybuylist, proxyselllist)	
								proxyselllist.remove(i);
								proxybuylist.remove(i);
								flag = getTradeCouple(list,proxybuylist,proxyselllist,tradeid);
								continue;
							}else{
								//退出循环换下一个循环进行执行
								//委卖网站上面没有钱了，买不了币了
//								 调整proxyselllist 数组,去掉第0个值,后续的值往前挪动
//							 	 调整proxybuylist 数组,去掉最后的一个值
//							 	 递归 GetTradeCouple(proxybuylist, proxyselllist)
								proxyselllist.remove(i);
								proxybuylist.remove(proxybuylist.size()-1);
								flag = getTradeCouple(list,proxybuylist,proxyselllist,tradeid);
								continue;
							}
						}else{
							//1.1.1.1 判断Buy所对应的网站的可交易币小于 0.01
							//去掉数组中的buy 和最后一个sell 做递归执行一次
							//调整proxybuylist 数组,去掉第0个值,后续的值往前挪动
							//调整proxyselllist数组,去掉最后的一个值
							//递归 GetTradeCouple(proxybuylist, proxyselllist)
							proxybuylist.remove(i);
							proxyselllist.remove(proxyselllist.size()-1);
							flag = getTradeCouple(list,proxybuylist,proxyselllist,tradeid);
							continue;
						}
					}else{
						//1.1.2不符合我们价差交易
						break;
					}
			   }else{
				   //2.不符合要求不继续循环马上跳出来
				   break;
			   }
		   }
		return flag;
	}
	
	/**
	 * 循环获取交易深度
	 * 把它按照买和卖分别存储
	 **/
	public boolean addDepthRec(String tradeid,ByteCoinTradeForListEntity bctfl){
		boolean flag = false;
		try {
			List<ByteCoinTradeEntity> selllist = bctfl.getSelllist();
			for (int i = 0; i < selllist.size(); i++) {
				ByteCoinTradeEntity bcte = selllist.get(i);
				BtcCoinTradeDepthRec bcdr = new BtcCoinTradeDepthRec();
				bcdr.setMarketid(bcte.getMarketid());
				bcdr.setTradeid(tradeid);
				bcdr.setType(1); //委卖是1 委买是0
				bcdr.setPrice(bcte.getPrice());
				bcdr.setQty(bcte.getQty());
				bcdr.setDate(DateUtil.convert2String(-1));
				bcdr.setTs(new Date());
				this.ibtcdao.addBtcCoinTradeDepthRec(bcdr);
			}
			List<ByteCoinTradeEntity> buylist = bctfl.getBuylist();			
			for (int i = 0; i < buylist.size(); i++) {
				ByteCoinTradeEntity bcte = buylist.get(i);
				BtcCoinTradeDepthRec bcdr = new BtcCoinTradeDepthRec();
				bcdr.setMarketid(bcte.getMarketid());
				bcdr.setTradeid(tradeid);
				bcdr.setType(0); //委卖是1 委买是0
				bcdr.setPrice(bcte.getPrice());
				bcdr.setQty(bcte.getQty());
				bcdr.setDate(DateUtil.convert2String(-1));
				bcdr.setTs(new Date());
				this.ibtcdao.addBtcCoinTradeDepthRec(bcdr);
			}
			flag = true;
		} catch (Exception e) {
			log.error("存储买和卖当时的快照",e);
		}
		return flag;
	}

	/**
	 * 获取FireCoin的一个委卖的交易深度
	 **/
	private ByteCoinTradeEntity getFireCoinSelling(Integer curor,FireCoinDepth fc){
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
		try {
			int i = 1;
//			List<FireCoinDepthSingle> list = fc.getSells();
			List<BigDecimal[]> list = fc.getAsks();
			//将委卖数组从低到高排序
			Collections.sort(list, new CompareFireCoinSellSort2());
			/*System.out.println("firecoin 委卖价格低到高排序");
			for (BigDecimal[] strs : list) {
				System.out.println("2390====>"+strs[0]);
			}
			System.out.println("firecoin 价格低到高排序");*/
			if (curor.intValue() <= list.size()) {
				for (BigDecimal[] strs : list) {
//					System.out.println("1406====>"+fcds.getPrice());
					if (i == curor.intValue()) {
						bcte.setChargefee(0.0);
						bcte.setChargefee(0.0);
						bcte.setCurtype(0);
						bcte.setMarketid(5);
						bcte.setPrice(strs[0]);
						bcte.setQty(strs[1]);
						bcte.setTradetype(0);
						bcte.setUscurreny(1.0);
						break;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bcte;
	}
	/**
	 * 获取FireCoin的一个委买交易深度
	 **/
	private ByteCoinTradeEntity getFireCoinBuying(Integer curor,FireCoinDepth fc){
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
		try {
			int i = 1;
//			List<FireCoinDepthSingle> list = fc.getBuys();
			List<BigDecimal[]> list = fc.getBids();
			//将委买数组从高到低排序
			Collections.sort(list, new CompareFireCoinBuyerSort2());
//			System.out.println("firecoin 委买价格高到低排序");
//			for (BigDecimal[] strs : list) {
//				System.out.println("1440====>"+strs[0]);
//			}
//			System.out.println("firecoin 价格 从高到低排序");
			if (curor.intValue() <= list.size()) {
				for (BigDecimal[] strs : list) {
//					System.out.println("1437====>"+fcds.getPrice());
					if (i == curor.intValue()) {
						bcte.setChargefee(0.0);
						bcte.setChargefee(0.0);
						bcte.setCurtype(0);
						bcte.setMarketid(5);
						bcte.setPrice(strs[0]);
						bcte.setQty(strs[1]);
						bcte.setTradetype(1);
						bcte.setUscurreny(1.0);
						break;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bcte;
	}
	private ByteCoinTradeEntity getOKCoinBuying(Integer curor,OKCoinMarketDepthEntity mde) {
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
//		String str = OKCoinClient.getInfos("https://www.okcoin.com/api/depth.do", null, "UTF8", true);
//		System.out.println("OKCOIN depth"+str);
//		 mde = JSONObject.parseObject(str,OKCoinMarketDepthEntity.class);
		List<Double[]> okccoinbids = mde.getBids();
		Collections.sort(okccoinbids, new CompareOKCoinBuyerSort());
		// 模拟okcoin的交易深度
//		 List<Double[]> okccoinbids = getOKCoinBids();//模拟OKCoin的委买交易深度
		
		  /*System.out.println("OKCOIN===委买"); 
		  for (Double[] doubles2 :okccoinbids) { 
			  System.out.println(doubles2[0]+"<------>"+doubles2[1]); 
		  }*/
		 
		int i = 1;
		if (curor.intValue() <= okccoinbids.size()) {
			for (Double[] doubles : okccoinbids) {
				if (i == curor.intValue()) {
					bcte.setChargefee(0.0);
					bcte.setChargefee(0.0);
					bcte.setCurtype(0);
					bcte.setMarketid(2);
					bcte.setPrice(BigDecimal.valueOf(doubles[0]));
					bcte.setQty(BigDecimal.valueOf(doubles[1]));
					bcte.setTradetype(1);
					bcte.setUscurreny(1.0);
					break;
				}
				i++;
			}
		}
		/* System.out.println("OKCOIN的委买第i个记录是=====>"+curor.intValue()+"====="+bcte.getPrice()+"===qty=="+bcte.getQty()); */
		return bcte;
	}

	private ByteCoinTradeEntity getOKCoinSelling(Integer curor,OKCoinMarketDepthEntity mde) {
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
		/*String str = OKCoinClient.getInfos("https://www.okcoin.com/api/depth.do", null, "UTF8", true);
		OKCoinMarketDepthEntity mde = JSONObject.parseObject(str,OKCoinMarketDepthEntity.class);*/
		List<Double[]> okccoinasks = mde.getAsks();
//		List<Double[]> okccoinasks = getOKCoinSels();//模拟OKCoin的委卖交易深度
//	    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Collections.sort(okccoinasks, new CompareOKCoinSellerSort());
//		System.out.println("OKCOIN===委卖"); 
//		for (Double[] doubles2 :okccoinasks) { 
//		  	System.out.println(doubles2[0]+"<---@@@22@--->"+doubles2[1]); 
//		 }
//		  System.out.println("OKCOIN===委卖------结束");
		int i = 1;
		if (curor.intValue() <= okccoinasks.size()) {
			for (Double[] doubles : okccoinasks) {
				if (i == curor.intValue()) {
					bcte.setChargefee(0.0);
					bcte.setChargefee(0.0);
					bcte.setCurtype(0);
					bcte.setMarketid(2);
					bcte.setPrice(BigDecimal.valueOf(doubles[0]));
					bcte.setQty(BigDecimal.valueOf(doubles[1]));
					bcte.setTradetype(0);
					bcte.setUscurreny(1.0);
					break;
				}
				i++;
			}
		}
		/* System.out.println("OKCOIN的委卖第i个记录是=====>"+curor.intValue()+"====="+bcte.getPrice()+"===qty=="+bcte.getQty()); */
		return bcte;
	}
	/**
	 * param ByteCoinVo 根据给定的VO信息进行循环 返回一个对象此对象里面有一个买的数组和一个卖的数组
	 */
	@SuppressWarnings("unchecked")
	public ByteCoinTradeForListEntity getByteCoinTradeEntity(ByteCoinVo bcv,String tradeid) {
		ByteCoinTradeForListEntity bctfl = new ByteCoinTradeForListEntity();
		try {
			//获取BtcChinaMarket
			BtcChinaMarketDepthEntity bd = null;
			if (BtcAction.BTCHINA_BTC_MARKET_ID.intValue() != 0) {
				 bd = BtcChinaUtil.getBtcChinaMarketDepthEntityInfo();
			}
			// 获取OKCoin的交易信息
			OKCoinMarketDepthEntity okdepth = null;
			ByteCoinTradeEntity okcoinbuy = null;
			ByteCoinTradeEntity okcoinsell = null;
			if (BtcAction.OK_BTC_MARKET_ID.intValue() != 0) {
				 okdepth    = OkCoinUtil.getOKCoinDepth();
				 okcoinbuy  = getOKCoinBuying(bcv.getCursor(),okdepth);
				 okcoinsell = getOKCoinSelling(bcv.getCursor(),okdepth);
			}
			//火币
			FireCoinDepth fc =  null;
			ByteCoinTradeEntity firecoinbuy  = null;
			ByteCoinTradeEntity firecoinsell = null;
			if(BtcAction.FIRECOIN_BTC_MARKET_ID.intValue()!=0){
//				 fc =  queryFireDepth();
				 fc = FireCoinUtil.getFireCoinDepthInfo();
				 firecoinbuy  = getFireCoinBuying(bcv.getCursor(), fc);
				 firecoinsell = getFireCoinSelling(bcv.getCursor(),fc);
			}
			//委卖数组
			List<ByteCoinTradeEntity> slist = new ArrayList<ByteCoinTradeEntity>();
			if(BtcAction.FIRECOIN_BTC_MARKET_ID.intValue()!=0){
				if(firecoinsell!=null){
					slist.add(firecoinsell);
				}else{
					firecoinsell.setMarketid(5);
					firecoinsell.setMessage("获取火币网交易深度返回失败，放弃本次循环!");
					slist.add(firecoinsell);
					bctfl.setFlag(2);
					return bctfl;
				}
			}
			if (BtcAction.BTCHINA_BTC_MARKET_ID.intValue() != 0) {
				ByteCoinTradeEntity btcchinasell = this.getBtcChinaSelling(bcv.getCursor(),bd);
				if (btcchinasell != null) {
					slist.add(btcchinasell);
				} else {
					btcchinasell.setMarketid(1);
					btcchinasell.setMessage("获取BTCCHINA交易深度返回失败，放弃本次循环!");
					slist.add(btcchinasell);
					bctfl.setFlag(2);
					return bctfl;
				}
			}
			if (BtcAction.OK_BTC_MARKET_ID.intValue() != 0) {
				/* System.out.println("okcoin卖"+okcoinsell.getPrice().doubleValue()); */
//				System.out.println("OKCOIN委卖"+ firecoinsell.getPrice().doubleValue());
				if (okcoinsell != null) {
					slist.add(okcoinsell);
				} else {
					okcoinsell.setMarketid(2);
					okcoinsell.setMessage("获取OKCOIN交易深度返回失败，放弃本次循环!");
					slist.add(okcoinsell);
					bctfl.setFlag(4);
					return bctfl;
				}
			}
		
			//委买数组
			List<ByteCoinTradeEntity> blist = new ArrayList<ByteCoinTradeEntity>();
			if(BtcAction.FIRECOIN_BTC_MARKET_ID.intValue()!=0){
//				System.out.println("火币委买"+ firecoinbuy.getPrice().doubleValue());
				if(firecoinbuy!=null){
					blist.add(firecoinbuy);
				}else{
					firecoinbuy.setMarketid(5);
					firecoinbuy.setMessage("获取火币网交易深度返回失败，放弃本次循环!");
					blist.add(firecoinbuy);
					bctfl.setFlag(2);
					return bctfl;
				}
			}
			if (BtcAction.BTCHINA_BTC_MARKET_ID.intValue() != 0) {
				
				ByteCoinTradeEntity btcchinabuy = this.getBtcChinaBuying(bcv.getCursor(),bd);
//				System.out.println("btcchina委买"+ btcchinabuy.getPrice().doubleValue());
				if (btcchinabuy != null) {
					blist.add(btcchinabuy);
				} else {
					btcchinabuy.setMarketid(1);
					btcchinabuy.setMessage("获取BTCC交易深度返回失败，放弃本次循环!");
					blist.add(btcchinabuy);
					bctfl.setFlag(1);
					return bctfl;
				}
			}
			if (BtcAction.OK_BTC_MARKET_ID.intValue() != 0) {
//				System.out.println("okcoin委买"+ okcoinbuy.getPrice().doubleValue());
				if (okcoinbuy != null) {
					blist.add(okcoinbuy);
				} else {
					okcoinbuy.setMarketid(2);
					okcoinbuy.setMessage("获取OKCOIN交易深度返回失败，放弃本次循环!");
					blist.add(okcoinbuy);
					bctfl.setFlag(3);
					return bctfl;
				}
			}
			
			Collections.sort(slist, new CompareSellerSort());
			/*for (ByteCoinTradeEntity byteCoinTradeEntity : slist) {
			  System.out.println(" 委卖数组"+byteCoinTradeEntity.getMarketname()+"==="+byteCoinTradeEntity.getPrice()); 
			}*/
			Collections.sort(blist, new CompareBuyerSort());
			/*for (ByteCoinTradeEntity byteCoinTradeEntity2 : blist) {
			  System.out.println("委买数组"+byteCoinTradeEntity2.getMarketname()+"==="+byteCoinTradeEntity2.getPrice()); 
			}*/
			bctfl.setBuylist(blist);
			bctfl.setSelllist(slist);
		} catch (Exception e) {
			log.error("对比交易队列返回列表", e);
			return null;
		}
		return bctfl;
	}
	

	private ByteCoinTradeEntity getBtcChinaBuying(Integer curor,BtcChinaMarketDepthEntity re)throws Exception {
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
		try {
			/*String str = bcc.execCoreMethod("getMarketDepth2");
			BtcChinaMarketDepthEntity re = JSONObject.parseObject(str,BtcChinaMarketDepthEntity.class);*/
			List<BtcChinaMarketDEUnit> buylists = re.getResult().getMarket_depth().getBid();
			//将此数组从小到大进行一次排序
			Collections.sort(buylists, new CompareBtcChinaBuySort());
//			List<BtcChinaMarketDEUnit> buylists = getBTCChinaBids();
			// //模拟BTCChina的买入深度
//			System.out.println("BTCChina 委买的开始价格");
//			 for (BtcChinaMarketDEUnit bctde : buylists) { 
//				  	System.out.println("BTCCHINA--->"+bctde.getPrice()+"<------>"+bctde.getAmount()); 
//		     }
//			System.out.println("BTCChina 委买的结束价格");
			int i = 1;
			if (curor.intValue() <= buylists.size()) {
				for (BtcChinaMarketDEUnit bcme : buylists) {
					if (i == curor.intValue()) {
						bcte = new ByteCoinTradeEntity();
						bcte.setChargefee(BtcChinaUtil.BTCCHINA_CHARGE.doubleValue());
						bcte.setCurtype(0);
						bcte.setMarketid(1);
						bcte.setPrice(bcme.getPrice());
						/* System.out.println("307-------------->"+bcme.getPrice()); */
						bcte.setQty(bcme.getAmount());
						bcte.setTradetype(1);
						break;
					}
					i++;
				}
			}
			/* System.out.println("BTCChina的委买第i个记录是=====>"+curor.intValue()+"====="+bcte.getPrice()+"===qty=="+bcte.getQty()); */
		} catch (Exception e) {
			log.error("获取BtcChina委买的数据", e);
			throw new Exception("获取BtcChina委买的数据", e);
		}
		return bcte;
	}

	private ByteCoinTradeEntity getBtcChinaSelling(Integer curor,BtcChinaMarketDepthEntity re)
			throws Exception {
		ByteCoinTradeEntity bcte = new ByteCoinTradeEntity();
		try {
			List<BtcChinaMarketDEUnit> selllists = re.getResult().getMarket_depth().getAsk();
			Collections.sort(selllists, new CompareBtcChinaSellSort());
//			 List<BtcChinaMarketDEUnit> selllists = getBTCChinaSells();//模拟BTCChina的卖出深度
//			 System.out.println("BTCChina 处理后委卖的价格"); for (BtcChinaMarketDEUnit
//			  bctde : selllists) { System.out.println(bctde.getPrice()+"<------>"+bctde.getAmount()); }
			 
			int i = 1;
			if (curor.intValue() <= selllists.size()) {
				for (BtcChinaMarketDEUnit bcme : selllists) {
					if (i == curor.intValue()) {
//						System.out.println("BtcChinaUtil.BTCCHINA_CHARGE=>"+BtcChinaUtil.BTCCHINA_CHARGE);
						bcte.setChargefee(BtcChinaUtil.BTCCHINA_CHARGE.doubleValue());
						bcte.setCurtype(0);
						bcte.setMarketid(1);
						bcte.setPrice(bcme.getPrice());
						bcte.setQty(bcme.getAmount());
						bcte.setTradetype(0);
						break;
					}
					i++;
				}
			}
			/* System.out.println("BTCChina的委卖第i个记录是=====>"+curor.intValue()+"====="+bcte.getPrice()+"===qty=="+bcte.getQty()); */
		} catch (Exception e) {
			log.error("获取BtcChina委卖的数据", e);
			throw new Exception("获取BtcChina委卖的数据", e);
		}
		return bcte;
	}
	
	/**
	 *造出三个委卖交易队列数组 
	 **/
	public BtcCoinTradeMarketInfo makeBtcCoinTradeMarketInfo(ByteCoinTradeForListEntity bctfl,String tradeid) throws Exception{
		BtcCoinTradeMarketInfo bctmi = new BtcCoinTradeMarketInfo();
		boolean flag = false;
		try {
			List<BtcCoinTradeMarket> list = new ArrayList<BtcCoinTradeMarket>();
			List<ByteCoinTradeEntity> buylist = bctfl.getBuylist();
			List<ByteCoinTradeEntity> selllist = bctfl.getSelllist();
			flag = this.getTradeCouple(list,buylist,selllist,tradeid);
			if (flag) {
				bctmi.setResult("<b><i>获取交易深度成功,已经成功凑成交易对!</i></b>");
			}else{
				bctmi.setResult("获取交易深度成功,没有成功凑成交易对!");
			}
			bctmi.setList(list);
		} catch (Exception e) {
			log.error("凑交易对失败!",e);
			bctmi.setResult("获取交易深度失败,没有匹配交易对!!"+e.getMessage());
		}
		return bctmi;
	}
	/**
	 *获取账户信息 
	 **/
	public List<AccountInfoEntity> findAllAccount(AccountInfoEntity aie){
		List<AccountInfoEntity>  list = ibtcdao.findAllAccount(aie);
		list = makeAccount(list);
		return list;
	}
	
	private List<AccountInfoEntity> makeAccount(List<AccountInfoEntity>  list){
		for (int i = 0; i < list.size(); i++) {
			AccountInfoEntity aie = list.get(i);
			switch (Integer.parseInt(aie.getMarketid())) {
				case 1:
					//btcchina
					aie = this.makeBtcChinaInfo(aie);
					list.set(i, aie);
					break;
				case 2:
					//okcoin
					aie = this.makeOKCoinInfo(aie);
					list.set(i, aie);
					break;
				case 5:
					//firecoin
					aie = this.makeFireCoinInfo(aie);
					list.set(i, aie);
					break;
			}
		}
		return list;
	}
	private AccountInfoEntity makeFireCoinInfo(AccountInfoEntity aie){
		aie = FireCoinUtil.getFireCoinBalance(aie.getTradecode(),aie.getPwd());
		BtcAction.FIRECOIN_ACCOUNT = aie;
		return aie;
	}
	private AccountInfoEntity makeOKCoinInfo(AccountInfoEntity aie){
		aie = OkCoinUtil.getOKCoinBalance(aie.getTradecode(),aie.getPwd());
		BtcAction.OKCOIN_ACCOUNT = aie;
		return aie;
	}
	private AccountInfoEntity makeBtcChinaInfo(AccountInfoEntity aie){
		aie = BtcChinaUtil.getBtcChinaBalance(aie.getTradecode(),aie.getPwd());
		BtcChinaUtil.BTCCHINA_CHARGE = (aie.getRate()==null?(new BigDecimal(0.0)):aie.getRate());
		BtcAction.BTCCHINA_ACCOUNT = aie;
		return aie;
	}
	
	/**
	 * @return the ibtcdao
	 */
	public IBtcDao getIbtcdao() {
		return ibtcdao;
	}
	/**
	 * @param ibtcdao the ibtcdao to set
	 */
	public void setIbtcdao(IBtcDao ibtcdao) {
		this.ibtcdao = ibtcdao;
	}

	
	public String removeTailZero(BigDecimal b) {
		 String s = b.toString();
		 int i, len = s.length();
		 for (i = 0; i < len; i++){
			  if (s.charAt(len - 1 - i) != '0'){
				  break;
			  }
		 }
		 if (s.charAt(len - i - 1) == '.'){
			 return s.substring(0, len - i - 1);
		 }
		 return s.substring(0, len - i);
	}
	
}
