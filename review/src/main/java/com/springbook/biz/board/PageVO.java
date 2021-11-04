package com.springbook.biz.board;

public class PageVO {

	//페이지 처리 중 시작 페이지로 보여질 변수
	private int startPage;
	// 끝 페이지번호를 보여줄 변수
	private int endPage;
	//이전, 이후 버튼에 투입될 변수
	private boolean prev, next;
	
	//총 받은 데이터수?
	private int total;
	//기준점. 즉 현재 페이지번호, 보여질 페이지 갯수 등이 들어가있음
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//시작 페이지, 마지막 페이지 계싼
		//cri.pageNum이 최초 1로 설정되므로 초기 값은 10으로 셋팅
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//endPage가 최초 10으로 설정 되어서 해당 변수는 최초 1로 설정됨
		this.startPage = this.endPage -9;
		
		int realEnd = (int)(Math.ceil(total * 1.0) / cri.getAmount());
		
		//실제 end페이지의 수가 초기 설정된 endPage보다 작아지면, endPage는 실제 endPage번호로 변경
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//이전, 다음 버튼 표출여부를 결정
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotal() {
		return total;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
	
}
