package com.springbook.biz.board;

public class PageVO {

	//������ ó�� �� ���� �������� ������ ����
	private int startPage;
	// �� ��������ȣ�� ������ ����
	private int endPage;
	//����, ���� ��ư�� ���Ե� ����
	private boolean prev, next;
	
	//�� ���� �����ͼ�?
	private int total;
	//������. �� ���� ��������ȣ, ������ ������ ���� ���� ������
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//���� ������, ������ ������ ���
		//cri.pageNum�� ���� 1�� �����ǹǷ� �ʱ� ���� 10���� ����
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		//endPage�� ���� 10���� ���� �Ǿ �ش� ������ ���� 1�� ������
		this.startPage = this.endPage -9;
		
		int realEnd = (int)(Math.ceil(total * 1.0) / cri.getAmount());
		
		//���� end�������� ���� �ʱ� ������ endPage���� �۾�����, endPage�� ���� endPage��ȣ�� ����
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//����, ���� ��ư ǥ�⿩�θ� ����
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
