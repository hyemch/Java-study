package company;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
	private final List<Worker> workers;

	public ManagerService() {
		this.workers = new ArrayList<Worker>();
	}

	public void addWorker(Worker worker) {
		this.workers.add(worker);
	}

	public void showAllSalaryInfo() {
		workers.forEach(worker -> showSalaryInfo(worker.name));
	}

	public void showSalaryInfo(String name) {
		for (Worker worker : workers) {
			if (worker.name.equals(name)) {
				worker.showSalaryInfo(name);
				return;
			}
		}
		System.out.println("해당 이름의 사원이 존재하지 않습니다.");
	}

	public void showTotalSalary() {
		int totalSalary = 0;
		for (Worker worker : workers) {
			totalSalary += worker.getPay();
		}
		System.out.printf("모든 사원들의 급여 총액은 : %,d원%n", totalSalary);
	}
}
