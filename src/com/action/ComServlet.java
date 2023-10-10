package com.action;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		//String date2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		
		if(method.equals("addbm")){  //������Ϣ   ***********����ע��  
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String str=cBean.getString("select id from bm where mc='"+mc+"'");
			if(str==null){
				int flag = cBean.comUp("insert into bm(mc,bz)  values('"+mc+"','"+bz+"' )");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "�����ɹ���");
					request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "����ʧ�ܣ�");
					request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
				} 
			}
			else{
				request.setAttribute("message", "��Ϣ�ظ���");
				request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upbm")){ //�޸Ĳ�����Ϣ 
			String id=request.getParameter("id");
			String mc = request.getParameter("mc"); 
			String bz = request.getParameter("bz");  
			String str=cBean.getString("select bz from bm where mc='"+mc+"' and id!='"+id+"'");
			if(str==null){
				int flag = cBean.comUp("update bm set mc='"+mc+"',bz='"+bz+"' where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "�����ɹ���");
					request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "����ʧ�ܣ�");
					request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
				}  
			}
			else{
				request.setAttribute("message", "��Ϣ�ظ���");
				request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delbm")){//ɾ��������Ϣ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from bm where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "ϵͳά���У����Ժ����ԣ�");
				request.getRequestDispatcher("admin/bm/index.jsp").forward(request, response);
			}
		}  
		
		else if(method.equals("addyg")){  //����Ա����Ϣ  
			String bh = request.getParameter("bh");     
			String xm = request.getParameter("xm");     
			String bm = request.getParameter("bm");     
			String nl = request.getParameter("nl");     
			String xb = request.getParameter("xb");     
			String xl = request.getParameter("xl");     
			String zy = request.getParameter("zy");     
			String zw = request.getParameter("zw");     
			String dh = request.getParameter("dh");     
			String xx = request.getParameter("xx");     
			String mm = request.getParameter("mm");   
			String str=cBean.getString("select xm from yg where bh='"+bh+"'");
			if(str==null){
				int flag = cBean.comUp("insert into yg(bh,xm,bm,nl,xb,xl,zy,zw,dh,xx,sj,mm) " +
						"values('"+bh+"','"+xm+"','"+bm+"','"+nl+"','"+xb+"','"+xl+"','"+zy+"','"+zw+"','"+dh+"','"+xx+"','"+date+"','"+mm+"' )");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "�����ɹ���");
					request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "����ʧ�ܣ�");
					request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
				}  
			}
			else{
				request.setAttribute("message", "����ظ���");
				request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
			} 
		} 
		else if(method.equals("upyg")){ // Ա����Ϣ 
			String id=request.getParameter("id");
			String bh = request.getParameter("bh");     
			String xm = request.getParameter("xm");     
			String bm = request.getParameter("bm");     
			String nl = request.getParameter("nl");     
			String xb = request.getParameter("xb");     
			String xl = request.getParameter("xl");     
			String zy = request.getParameter("zy");     
			String zw = request.getParameter("zw");     
			String dh = request.getParameter("dh");     
			String xx = request.getParameter("xx");     
			String mm = request.getParameter("mm");  
			String str=cBean.getString("select xm from yg where bh='"+bh+"' and id!='"+id+"'");
			if(str==null){
				int flag = cBean.comUp("update yg set bh='"+bh+"',xm='"+xm+"',bm='"+bm+"',nl='"+nl+"',xb='"+xb+"'," +
						"xl='"+xl+"',zy='"+zy+"',zw='"+zw+"',dh='"+dh+"',xx='"+xx+"',mm='"+mm+"' where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "�����ɹ���");
					request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "����ʧ�ܣ�");
					request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
				} 
			}
			else{
				request.setAttribute("message", "����ظ���");
				request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delyg")){//ɾ��Ա����Ϣ
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from yg where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/yg/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("upyg2")){ // Ա����Ϣ 
			String username=(String)session.getAttribute("user"); 
			String bh = request.getParameter("bh");     
			String xm = request.getParameter("xm");     
			String bm = request.getParameter("bm");     
			String nl = request.getParameter("nl");     
			String xb = request.getParameter("xb");     
			String xl = request.getParameter("xl");     
			String zy = request.getParameter("zy");     
			String zw = request.getParameter("zw");     
			String dh = request.getParameter("dh");     
			String xx = request.getParameter("xx");     
			String mm = request.getParameter("mm");   
				int flag = cBean.comUp("update yg set xm='"+xm+"',bm='"+bm+"',nl='"+nl+"',xb='"+xb+"'," +
						"xl='"+xl+"',zy='"+zy+"',zw='"+zw+"',dh='"+dh+"',xx='"+xx+"' where bh='"+username+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "�����ɹ���");
					request.getRequestDispatcher("admin/yg/index2.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "����ʧ�ܣ�");
					request.getRequestDispatcher("admin/yg/index2.jsp").forward(request, response);
				}  
		}  
		
		 
		else if(method.equals("addjh")){  //���ӹ����ƻ�   String bh="";String nr="";String qx="";  
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");     
			String qx = request.getParameter("qx");     
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'");   
			String username=(String)session.getAttribute("user");
			int flag = cBean.comUp("insert into jh(bh,nr,qx,sj,xm ,gjyh) values('"+bh+"','"+nr+"','"+qx+"','"+date+"','"+xm+"','"+username+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("upjh")){ // �����ƻ� 
			String id=request.getParameter("id");
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");     
			String qx = request.getParameter("qx");     
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'"); 
			int flag = cBean.comUp("update jh set bh='"+bh+"',nr='"+nr+"',qx='"+qx+"',xm='"+xm+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("deljh")){//ɾ�������ƻ�
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from jh where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/jh/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("shjh")){ // �����ƻ� 
			String id=request.getParameter("id");
			String zt = request.getParameter("zt");   
			int flag = cBean.comUp("update jh set zt='"+zt+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/jh/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/jh/index2.jsp").forward(request, response);
			}  
		} 
		
		
		else if(method.equals("addgz")){  //���Ӹ���   
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");      
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'");   
			String username=(String)session.getAttribute("user");
			int flag = cBean.comUp("insert into gz(bh,nr ,sj,xm ,gjyh) values('"+bh+"','"+nr+"' ,'"+date+"','"+xm+"','"+username+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("upgz")){ // ���� 
			String id=request.getParameter("id");
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");     
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'"); 
			int flag = cBean.comUp("update gz set bh='"+bh+"',nr='"+nr+"' ,xm='"+xm+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delgz")){//ɾ������
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from gz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/gz/index.jsp").forward(request, response);
			}
		}  
		
		
		else if(method.equals("addkh")){  //���ӿ���    
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");     
			String qx = request.getParameter("qx");     
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'");   
			String username=(String)session.getAttribute("user");
			int flag = cBean.comUp("insert into kh(bh,nr,qx,sj,xm ,gjyh) values('"+bh+"','"+nr+"','"+qx+"','"+date+"','"+xm+"','"+username+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("upkh")){ // ���� 
			String id=request.getParameter("id");
			String bh = request.getParameter("bh");     
			String nr = request.getParameter("nr");     
			String qx = request.getParameter("qx");     
			String xm = cBean.getString("select xm from yg where bh='"+bh+"'"); 
			int flag = cBean.comUp("update kh set bh='"+bh+"',nr='"+nr+"',qx='"+qx+"',xm='"+xm+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delkh")){//ɾ������
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from kh where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/kh/index.jsp").forward(request, response);
			}
		}  
		
		
		
		
		else if(method.equals("addrz")){  //������־ 
			String username=(String)session.getAttribute("user");
			String bt = request.getParameter("bt");     
			String nr = request.getParameter("nr");     
			String xm = cBean.getString("select xm from yg where bh='"+username+"'");    
			int flag = cBean.comUp("insert into rz(bh,bt,nr,sj,xm ) values('"+username+"','"+bt+"','"+nr+"','"+date+"','"+xm+"' )");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("uprz")){ // ��־ 
			String id=request.getParameter("id");
			String username=(String)session.getAttribute("user");
			String bt = request.getParameter("bt");     
			String nr = request.getParameter("nr");     
			String xm = cBean.getString("select xm from yg where bh='"+username+"'");   
			int flag = cBean.comUp("update rz set bh='"+username+"',bt='"+bt+"',nr='"+nr+"',xm='"+xm+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delrz")){//ɾ����־
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from rz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/rz/index.jsp").forward(request, response);
			}
		}  
		else if(method.equals("shrz")){ // ��־ 
			String id=request.getParameter("id");
			String sh = request.getParameter("sh");  
			String yj = request.getParameter("yj");   
			int flag = cBean.comUp("update rz set sh='"+sh+"',yj='"+yj+"' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/rz/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/rz/index2.jsp").forward(request, response);
			}  
		} 
		else if(method.equals("delrz2")){//ɾ����־
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from rz where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "�����ɹ���");
				request.getRequestDispatcher("admin/rz/index2.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "����ʧ�ܣ�");
				request.getRequestDispatcher("admin/rz/index2.jsp").forward(request, response);
			}
		}  
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throcw ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
