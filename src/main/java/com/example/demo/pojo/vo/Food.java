package com.example.demo.pojo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.util.Util;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;

@JsonInclude(Include.NON_NULL)
public class Food implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	private String image;

	private String name;

	private Integer sales;

	private Double price;

	private String info;

	private Integer stock;

	private Integer order;

	private String menuClassifyId;

	@JsonIgnore
	private String weightList = "[]";
	@JsonIgnore
	private String tasteList = "[]";
	@JsonIgnore
	private String practiceList = "[]";

	private List<Weight> weightObjList = new ArrayList<Weight>();

	private List<Taste> tasteObjList = new ArrayList<Taste>();

	private List<Practice> practiceObjList = new ArrayList<Practice>();

	private Boolean isNorms = false;

	private List<Map<String, Weight>> weight = new ArrayList<Map<String, Weight>>();

	private List<String> taste = new ArrayList<String>();

	private List<String> practice = new ArrayList<String>();
	
	private Integer ishide;
	
	private String salesMode;
	
	private Float discount;
	public Food() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIshide() {
		return ishide;
	}

	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMenuClassifyId() {
		return menuClassifyId;
	}

	public void setMenuClassifyId(String menuClassifyId) {
		this.menuClassifyId = menuClassifyId;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getWeightList() {
		return weightList;
	}

	public void setWeightList(String weightList) {
		this.weightList = weightList;
	}

	public String getTasteList() {
		return tasteList;
	}

	public void setTasteList(String tasteList) {
		this.tasteList = tasteList;
	}

	public String getPracticeList() {
		return practiceList;
	}

	public void setPracticeList(String practiceList) {
		this.practiceList = practiceList;
	}
	
	public String getSalesMode() {
		return salesMode;
	}
	public void setSalesMode(String salesMode) {
		this.salesMode = salesMode;
	}
	
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public List<Map<String, Weight>> getWeight() {
		if (this.weightList == null || "".equals(this.weightList)) {
			return weight;
		} else {
			List<Weight> listWeight = Util.toObject(this.getWeightList(), new TypeReference<List<Weight>>() {
			});
			List<Map<String, Weight>> datas = new ArrayList<Map<String, Weight>>();
			Map<String, Weight> weigthMap = new HashMap<String, Weight>();
			for (int i = 1; i < listWeight.size() + 1; i++) {
				switch (i % 3) {
				case 1:
					weigthMap = new HashMap<String, Weight>();
					weigthMap.put("w1", listWeight.get(i - 1));
					if (i == listWeight.size()) {
						datas.add(weigthMap);
					}
					break;
				case 2:
					weigthMap.put("w2", listWeight.get(i - 1));
					if (i == listWeight.size()) {
						datas.add(weigthMap);
					}
					break;
				case 0:
					weigthMap.put("w3", listWeight.get(i - 1));
					datas.add(weigthMap);
					break;
				default:
					break;
				}
			}
			return datas;
		}
	}

	public void setWeight(List<Map<String, Weight>> weight) {
		this.weight = weight;
	}

	public List<String> getTaste() {
		if (this.tasteList == null || "".equals(this.tasteList)) {
			return taste;
		} else {
			List<Taste> listTaste = Util.toObject(this.getTasteList(), new TypeReference<List<Taste>>() {
			});
			List<String> datas = new ArrayList<String>();
			for (Taste t : listTaste) {
				datas.add(t.getName());
			}
			return datas;
		}
	}

	public void setTaste(List<String> taste) {
		this.taste = taste;
	}

	public List<String> getPractice() {
		if (this.practiceList == null || "".equals(this.practiceList)) {
			return practice;
		} else {
			List<Practice> listPractice = Util.toObject(this.getPracticeList(), new TypeReference<List<Practice>>() {
			});
			List<String> datas = new ArrayList<String>();
			for (Practice p : listPractice) {
				datas.add(p.getName());
			}
			return datas;
		}
	}

	public void setPractice(List<String> practice) {
		this.practice = practice;
	}

	public Boolean getIsNorms() {
		if (getPractice().size() == 0 && getTaste().size() == 0 && getWeight().size() == 0) {
			return false;
		}
		return true;
	}

	public void setIsNorms(Boolean isNorms) {
		this.isNorms = isNorms;
	}

	public List<Weight> getWeightObjList() {
		if (this.weightList == null || "".equals(this.weightList)) {
			return weightObjList;
		} else {
			return Util.toObject(weightList, new TypeReference<List<Weight>>() {
			});
		}
	}

	public void setWeightObjList(List<Weight> weightObjList) {
		this.weightObjList = weightObjList;
	}

	public List<Taste> getTasteObjList() {
		if (this.tasteList == null || "".equals(this.tasteList)) {
			return tasteObjList;
		} else {
			return Util.toObject(tasteList, new TypeReference<List<Taste>>() {
			});
		}
	}

	public void setTasteObjList(List<Taste> tasteObjList) {
		this.tasteObjList = tasteObjList;
	}

	public List<Practice> getPracticeObjList() {
		if (this.practiceList == null || "".equals(this.practiceList)) {
			return practiceObjList;
		} else {
			return Util.toObject(practiceList, new TypeReference<List<Practice>>() {
			});
		}
	}

	public void setPracticeObjList(List<Practice> practiceObjList) {
		this.practiceObjList = practiceObjList;
	}

	public Date getCreateTime() {
		System.out.println(createTime);
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}

class Weight implements Serializable {

	private static final long serialVersionUID = 1L;

	private String weight;

	private Double price;

	public Weight() {
		// TODO Auto-generated constructor stub
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}

class Taste implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public Taste() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

class Practice implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public Practice() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
