package com.sx.test;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.sx.btccoin.dao.IBtcDao;
import com.sx.btccoin.entity.AccountInfoEntity;
import com.sx.btccoin.entity.BtcCoinTradeDepthRec;
import com.sx.btccoin.entity.BtcCoinTradeMarket;
import com.sx.btccoin.entity.TradeLogEntity;
import com.sx.btccoin.util.analyse.OKCoinAnalyseUtil;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(
		locations = { 
				"classpath:/com/sx/config/applicationContext-common.xml",
				"/com/sx/config/applicationContext-dao.xml",
				"/com/sx/config/applicationContext-actions.xml" 
		}
)  
public class TestDao extends TestCase{
	@Autowired
	private IBtcDao btcdao;
	
	@Test
	public void testOKCoinTradesApi(){
//		1407675907
//		1407676047
//		1407676047
//		1407676048
//		1407676131399
		long a = Long.parseLong("1407676048000");
		System.out.println("1407675907000--"+new Date(a));
		
		System.out.println(System.currentTimeMillis()+"--"+new Date(System.currentTimeMillis()));
		
		OKCoinAnalyseUtil.getOKCoinMarketLTCDepthEntity();
	}
	@Test
	public void testTradeLog(){
		try {
			PropertyConfigurator.configure(Test.class.getClassLoader().getResource("com/sx/config/log4j.properties"));
			TradeLogEntity tle = new TradeLogEntity();
			tle.setStatus(0);
			tle.setTradeid("2014010122");
			List list= btcdao.findTradeLogEntity(tle);
			System.out.println(list.size()+"---->");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void testAddLog(){
		try {
			TradeLogEntity tle = new TradeLogEntity();
			tle.setMarketid(1);
			tle.setMethod("sell");
			tle.setNote("note");
			tle.setPrice(new BigDecimal(20.1));
			tle.setQty(new BigDecimal(10.2));
			tle.setResponse(new Integer(1));
			tle.setStatus(new Integer(2));
			tle.setTradeid("20140101");
			tle.setTradetype("btc");
			Integer count = btcdao.addBtcTradeLog(tle);
			System.out.println("log--->id=====>"+tle.getId().intValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void testMakeBtcInfo(){
		String str = "{\"result\":1,\"id\":\"0\"}";
		HashMap<String,Object> j = (HashMap<String,Object>)JSONObject.parseObject(str,HashMap.class);
		System.out.println(j.get("result"));
		System.out.println(j.get("id"));
	}
	
	@Test
	public void testQueryBtcCoinTradeMarket(){
//		List list = btcdao.;
//		System.out.println(list.size()+"---->");
		BigDecimal bd = new BigDecimal(10.112222);
		System.out.println(bd.toPlainString());
		System.out.println(bd.toEngineeringString());
		System.out.println(bd.toString());
		System.out.println(bd.doubleValue()+"");
	}
	@Test
	public void testAddBtcCoinTradeMarket(){
		try {
			BtcCoinTradeMarket bctm = new BtcCoinTradeMarket();
			bctm.setBuycharge(new BigDecimal(1.01));
			bctm.setBuymarketid(1);
			bctm.setBuymarketname("marketname");
			bctm.setBuyprice(new BigDecimal(10.1));
			bctm.setBuyqty(new BigDecimal(1.1));
			bctm.setBuytradestatus(2);
			bctm.setBuyusprice(new BigDecimal(2.1));
			bctm.setRate(1.11);
			bctm.setSellcharge(new BigDecimal(11.1));
			bctm.setSellmarketid(2);
			bctm.setSellmarketname("sellname");
			bctm.setSellprice(new BigDecimal(11.4));
			bctm.setSellqty(new BigDecimal(2.1));
			bctm.setSelltradestatus(1);
			bctm.setSellusprice(new BigDecimal(2.8));
			bctm.setSelltradestatus(1);
			bctm.setStatus(3);
			Integer a = btcdao.addBtcCoinTradeMarket(bctm);
			System.out.println("aaa===>"+a);
			System.out.println("id===>"+bctm.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAddDepth(){
		try {
			BtcCoinTradeDepthRec bctd = new BtcCoinTradeDepthRec();
			bctd.setMarketid(1);
			bctd.setPrice(new BigDecimal(2000.12));
			bctd.setUsprice(new BigDecimal(293.22));
			bctd.setQty(new BigDecimal(399.02));
			bctd.setType(1);
			bctd.setTradeid("1111");
			bctd.setTs(new Date());
			Integer s = btcdao.addBtcCoinTradeDepthRec(bctd);
			System.out.println(bctd.getId().intValue()+"<-----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
    @Test  
	public void testInfo(){
		 try {
			 AccountInfoEntity aie = new AccountInfoEntity();
			 aie.setStatus(1);
			 List list = btcdao.findAllAccount(aie);
//			 assertEquals(list.size(), 0);
			 this.assertSame(list.size(), 3);
			 System.out.println(list.size()+"====");
			 /*String c = "/sx/back/attachment/files/2013\\06\\19\\";
			 System.out.println(c.replaceAll("\\", "/"));*/
			 /*CoreEntity core = new CoreEntity();
			 core.setTitle("JUNIT========TEST");
			 coredao.saveInfo(core);
			 core.setTitle("JUNItestInfoEST");
			 coredao.saveInfo(core);
			 core.setTitle("FrameWorkTest");
			 coredao.saveInfo(core);*/
			
		 } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
