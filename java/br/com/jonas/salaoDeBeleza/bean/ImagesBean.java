package br.com.jonas.salaoDeBeleza.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesBean {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 6; i++) {
			images.add("nature" + i + ".jpg");
		}
	}

	public List<String> getImages() {
		return images;
	}

}
