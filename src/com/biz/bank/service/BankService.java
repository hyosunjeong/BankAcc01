package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {
	
	List<BankVO> bankList;
	String strFileName;
	
	public BankService(String strFileName) {
		bankList = new ArrayList();
		this.strFileName =strFileName;
		
	}
	
	public void readFile() {
		FileReader  fr;
		BufferedReader  buffer;
		
		try {
			fr = new FileReader(this.strFileName);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String reader = buffer.readLine();
				if(reader == null) break;
				
				String[] banks = reader.split(":");
				// banks[0] = id
				// banks[1] = balance
				// banks[2] = lastDate
				
				BankVO vo = new BankVO();
				vo.setStrId(banks[0]);
				vo.setIntBalance(Integer.valueOf(banks[1]));
				vo.setStrLastDate(banks[2]);
				bankList.add(vo);
				
				System.out.println(reader);
			}
			buffer.close();
			fr.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		}
	
	
	public void bankOutput() {
		
	
	Scanner scan = new Scanner(System.in);
	System.out.print("계좌번호 >> ");

	String strId = scan.nextLine();
	
	BankVO bankVO = null; 
	
	for(BankVO vo : bankList) {
	
			if(vo.getStrId().equals(strId)) {
				
				bankVO = vo;
				break;
			} 
			
		}
	// bankVO에 들어있는 값은 주소이다.
	if(bankVO == null) {
		System.out.println("없다.");
		return;
	}
	// 원잔액 추출
	int intBalance = bankVO.getIntBalance();
	
	System.out.print("출금액 >> ");
	String strOutput = scan.nextLine();
	int intOutput = Integer.valueOf(strOutput);
	
	// 원잔액과 출금액 비교
	if(intBalance < intOutput) {
		System.out.println("잔액이 부족하여 출금 못함");
		return;
	}
	
	
	
	int intAfterBalance = intBalance - Integer.valueOf(strOutput);
	bankVO.setIntBalance(intAfterBalance);
	
		// 현재 시스템의 날짜를 문자열로 가져오기
		// 1.8 이상에서만 작동
		String strDate = LocalDate.now().toString();
		bankVO.setStrLastDate(strDate);
	
	System.out.println("========================");
	System.out.println("출금이 완료 되었습니다.");
	System.out.println("------------------------");
	System.out.println("원잔액:" + intBalance);
	System.out.println("출금액:"+strOutput);
	System.out.println("현잔액:"+intAfterBalance);
	
	
	}

	public void bankInput() {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("계좌번호 >> ");
	
		String strId = scan.nextLine();
		
		BankVO bankVO = null; 
		
		for(BankVO vo : bankList) {
		
				if(vo.getStrId().equals(strId)) {
					
					bankVO = vo;
					break;
				} 
				
			}
		// bankVO에 들어있는 값은 주소이다.
		if(bankVO == null) {
			System.out.println("없다.");
			return;
		}
		// 원잔액 추출
		int intBalance = bankVO.getIntBalance();
		
		System.out.print("입금액 >> ");
		String strInput = scan.nextLine();
		
		int intAfterBalance = intBalance + Integer.valueOf(strInput);
		bankVO.setIntBalance(intAfterBalance);
		
		// 현재 시스템의 날짜를 문자열로 가져오기
		// 1.8 이상에서만 작동
		String strDate = LocalDate.now().toString();
		bankVO.setStrLastDate(strDate);
		
		
		System.out.println("========================");
		System.out.println("입금이 완료 되었습니다.");
		System.out.println("------------------------");
		System.out.println("원잔액:" + intBalance);
		System.out.println("입금액:"+strInput);
		System.out.println("현잔액:"+bankVO.getIntBalance());
		
						
				
		}
	
	public void bankView() {
		System.out.println("+++++++++++++++++++");
		System.out.println("계좌번호\t잔액\t최종거래일");
		
		for(BankVO vo: bankList) {
			System.out.printf("%5s\t%10d\t%s\n",
					vo.getStrId(),
					vo.getIntBalance(),
					vo.getStrLastDate());
		}
		System.out.println("+++++++++++++++++++");
		
	}
	
			
	}


