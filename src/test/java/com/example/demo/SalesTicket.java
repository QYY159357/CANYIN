package com.example.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * 打印消费票据的布局类 Created by Administrator on 2018\5\1 0001.
 */
public class SalesTicket implements Printable {

	private static final int PAGE_EXISTS = 0;
	private static final int NO_SUCH_PAGE = 0;

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Component c = null;
		// 转换成Graphics2D 拿到画笔
		Graphics2D g2 = (Graphics2D) graphics;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);

		// 打印起点坐标
		double x = pageFormat.getImageableX() + 20;
		double y = pageFormat.getImageableY() + 20;

		// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
		Font font = new Font("宋体", Font.PLAIN, 14);
		g2.setFont(font);// 设置字体
		float heigth = font.getSize2D();// 字体高度
		// 标题
		g2.drawString("迪乐尼婴童游泳馆", (float) x, (float) y + heigth);

		float line = 2 * heigth;
		font = new Font("宋体", Font.PLAIN, 8);
		g2.setFont(font);// 设置字体
		heigth = font.getSize2D();// 字体高度

		// 显示单据号
		line += heigth;
		g2.drawString("单据号：danjuhao", (float) x, (float) y + line);
		// 显示地址
		line += heigth;
		g2.drawString("门店地址：adress", (float) x, (float) y + line);
		// 显示电话
		line += heigth;
		g2.drawString("联系电话:  phoneNum", (float) x, (float) y + line);
		line += heigth;
		g2.drawString("卡类: cardCaregory", (float) x, (float) y + line);
		// 显示消费情况
		line += heigth;
		g2.drawString("消费项目 consumptionCaregory", (float) x, (float) y + line);
		line += heigth;
		g2.drawString("扣除（次）：", (float) x + 25, (float) y + line);
		line += heigth;
		g2.drawString("剩余（次）：shengyuNum", (float) x + 25, (float) y + line);
		// 会员信息
		line += heigth;
		g2.drawString("会员卡号：cardNum", (float) x, (float) y + line);
		line += heigth;
		g2.drawString("会员姓名：personRealName", (float) x, (float) y + line);
		line += heigth;
		g2.drawString("消费时间:xfDate", (float) x, (float) y + line);
		line += heigth;
		g2.drawString("开卡日期:makeCardDate", (float) x, (float) y + line);

		line += heigth;
		g2.drawString("敬请保留本小票", (float) x + 20, (float) y + line);

		g2.drawLine((int) x, (int) (y + line + 10), (int) x + 200, (int) (y + line + 10));

		switch (pageIndex) {
		case 0:
			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;

		}
	}

	public static void PrintSale(SalesTicket ticket) throws PrinterException {
		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);

		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper paper = new Paper();
		paper.setSize(580, 3000);// 纸张大小
		paper.setImageableArea(0, 0, 580, 30000);// A4(595 X
		// 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		pf.setPaper(paper);
		book.append(ticket, pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		// 设置打印类
		job.setPageable(book);

		job.print();

	}

//	public static void main(String[] args) {
//		try {
//			PrintSale(new SalesTicket());
//		} catch (PrinterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}