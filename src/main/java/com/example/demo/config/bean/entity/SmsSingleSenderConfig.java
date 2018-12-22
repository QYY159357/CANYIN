package com.example.demo.config.bean.entity;

import java.io.Serializable;

public class SmsSingleSenderConfig implements Serializable {

		private static final long serialVersionUID = 1L;

		private SmsSingleSenderParams smsSingleSenderParams;

		private TimeConfig timeConfig;

		public SmsSingleSenderConfig() {
			// TODO Auto-generated constructor stub
		}

		public SmsSingleSenderParams getSmsSingleSenderParams() {
			return smsSingleSenderParams;
		}

		public void setSmsSingleSenderParams(SmsSingleSenderParams smsSingleSenderParams) {
			this.smsSingleSenderParams = smsSingleSenderParams;
		}

		public TimeConfig getTimeConfig() {
			return timeConfig;
		}

		public void setTimeConfig(TimeConfig timeConfig) {
			this.timeConfig = timeConfig;
		}
		
		public class SmsSingleSenderParams implements Serializable {

			private static final long serialVersionUID = 1L;

			private String nationCode;

			private Integer templateId;

			private String sign;

			private String extend;

			private String ext;

			public SmsSingleSenderParams() {
				// TODO Auto-generated constructor stub
			}

			public String getNationCode() {
				return nationCode;
			}

			public void setNationCode(String nationCode) {
				this.nationCode = nationCode;
			}

			public Integer getTemplateId() {
				return templateId;
			}

			public void setTemplateId(Integer templateId) {
				this.templateId = templateId;
			}

			public String getSign() {
				return sign;
			}

			public void setSign(String sign) {
				this.sign = sign;
			}

			public String getExtend() {
				return extend;
			}

			public void setExtend(String extend) {
				this.extend = extend;
			}

			public String getExt() {
				return ext;
			}

			public void setExt(String ext) {
				this.ext = ext;
			}

		}

		public class TimeConfig implements Serializable {

			private static final long serialVersionUID = 1L;

			private Integer length;

			private Integer timeOut;

			private Integer countDown;

			public TimeConfig() {
				// TODO Auto-generated constructor stub
			}

			public Integer getLength() {
				return length;
			}

			public void setLength(Integer length) {
				this.length = length;
			}

			public Integer getTimeOut() {
				return timeOut;
			}

			public void setTimeOut(Integer timeOut) {
				this.timeOut = timeOut;
			}

			public Integer getCountDown() {
				return countDown;
			}

			public void setCountDown(Integer countDown) {
				this.countDown = countDown;
			}

		}

	}