package com.dgut.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dgut.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String upload(MultipartFile file, String path, String name) {
		System.out.println("path : " + path + ", name : " + name);
		FileOutputStream out = null;
		FileInputStream in = null;
		try {
			out = new FileOutputStream(path + name);
			in = (FileInputStream) file.getInputStream();

			byte[] buffers = new byte[1024];

			while (in.read(buffers) != -1) {
				out.write(buffers);
			}
			return name;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
