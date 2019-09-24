package huawei.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.huawei.bean.ReturnResultBean;
import com.huawei.service.LoginServiceImpl;
import com.huawei.session.HuaweiSession;

public class HuaweiTest {

	public static void main(String[] args) {
		System.out.println(Math.random());
		LoginServiceImpl serv = new LoginServiceImpl();
		HuaweiSession session = new HuaweiSession();
		ReturnResultBean resultBean = serv.getLoginPageToken(session);
		if (resultBean.getResultCode() == 0) {
			String token = (String) resultBean.getReturnObj();
			//resultBean = serv.getSid(token, session);
			if (resultBean.getResultCode() == 0) {
				//resultBean = serv.analysisHealth(token, session);
				if (resultBean.getResultCode() == 0) {
					String phoneNo = "18577035209";
					resultBean = serv.chkRisk(token, phoneNo, session);
					if (resultBean.getResultCode() == 0) {
						serv.getSMSCodeV3(token, phoneNo, session);
					}
				}
			}
		}
	}
}
