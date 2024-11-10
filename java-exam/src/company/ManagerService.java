package company;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
	private List<Worker> workers;

	public ManagerService() {
		this.workers = new ArrayList<Worker>();
	}

	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}

	public void showAllSalaryInfo() {
		//모든 사원의 정보와 급여 출력
		workers.forEach(worker -> showSalaryInfo(worker.name));
	}

	public void showSalaryInfo(String name) {
		//특정 이름의 사원 급여 정보 출력
		for (Worker worker : workers) {
			if (worker.name.equals(name)) {
				worker.showSalaryInfo(name);
				return;
			}
		}
		System.out.println("해당 이름의 사원이 존재하지 않습니다.");
	}

	public void showTotalSalary() {
		// 급여 총액 출력
		int totalSalary = 0;
		for (Worker worker : workers) {
			totalSalary += worker.getPay();
		}
		System.out.printf("모든 사원들의 급여 총액은 : %,d원%n", totalSalary);
	}
}
