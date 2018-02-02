package com.almundo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.domain.Call;
import com.almundo.domain.Employee;
import com.almundo.domain.Role;
import com.almundo.enums.Cargo;
import com.almundo.service.CustomerService;
import com.almundo.service.EmployeeService;
import com.almundo.service.RoleService;
import com.google.common.collect.Lists;

@RestController
@RequestMapping("/main")
public class Dispatcher {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleService roleService;

	private Integer llamadaEntrante = 1;
	private Timer temporizador;
	private Integer llamadasTotales = 0;
	private Set<Integer> conjuntoClientes =  new HashSet<>();
	private List<Integer> clientesLlamando = Lists.newArrayList();
	private Stack<Call> llamadasContestadas = new Stack<>();
	private Stack<Call> llamadasEnEspera =  new Stack<>();
	private Queue<Employee> operadores;
	private Queue<Employee> supervisores;
	private Queue<Employee> directores;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Boolean> dispatchCall() {
		
		cargarEmpleados();

		TimerTask timerTask = new TimerTask() {
			public void run() {
				
				int llamadasConcurrentes = (int) (Math.random() * 3) + 1;
				llamadasTotales += llamadasConcurrentes;
				System.out.println("------------------------------------------------"
						+ "-----------------------------------------------------------------");
				System.out.println("LLAMADA(S) ENTRANTES(S): " + llamadaEntrante++ + "    ---    " + "LLAMADA(S) CONCURRENTE(S): "
						+ llamadasConcurrentes + "    ---    " + "LLAMADAS TOTALES: " + llamadasTotales);
				System.out.println("------------------------------------------------"
					+ "-----------------------------------------------------------------");

				/*
				 * Genera una llamada aleatoriamente con un cliente de DB y consulta por un
				 * empleado libre segun restricciones operario, supervisor, director
				 */
				for (int i = 0; i < llamadasConcurrentes; i++) {
					int idCliente;
					/* Valida que no hayan llamadas entrantes repetidas */
					do {
						idCliente = (int) (Math.random() * 15) + 1;
					} while (conjuntoClientes.contains(idCliente));
					conjuntoClientes.add(idCliente);
					clientesLlamando.add(idCliente);
					Call call = new Call();
					call.setCustomer(customerService.findOne(Long.parseLong(idCliente + "")));	
					call.setTimeCall((int)(Math.random() * 5) + 5);
					llamadasContestadas.push(seleccionarContestador(call));
				}

				/*
				 * Consulta empleados de DB, asigna un tiempo aleatorio para la llamada entre 5
				 * y 10 minutos */
				System.out.printf("%-30s%-30s%-30s%-30s\n", "Cliente llamando", 
					"Empleado contestando", "Cargo del empleado", "Duración de la llamada");
				
				for (Call llamada : llamadasContestadas) 
						new ContestarLlamada(llamada.getCustomer().getName(), 
							llamada.getEmployee().getName(), llamada.getEmployee().getRole().getName(), llamada.getTimeCall()).start();
				
				if (llamadaEntrante == 8)
					reiniciar();
								
			}
		};

		// Aquí se pone en marcha el temporizador cada segundo.
		temporizador = new Timer();
		// Dentro de 0 milisegundos avísame cada 6000 milisegundos
		temporizador.scheduleAtFixedRate(timerTask, 0, 6000);

		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	class ContestarLlamada extends Thread {
		
		private String cliente;
		private String empleado;
		private String cargo;
		private Integer duracionLlamada;

		public ContestarLlamada(String cliente, String empleado, String cargo, Integer duracionLlamada) {
			this.cliente = cliente;
			this.empleado = empleado;
			this.cargo = cargo;
			this.duracionLlamada = duracionLlamada;
		}

		public void run() {
			System.out.printf("%-30s%-30s%-30s%-30s\n", cliente, empleado, cargo, duracionLlamada + "s.");
		}

	}
	
	private Call seleccionarContestador(Call call) {
		if(!operadores.isEmpty()) {
			call.setEmployee(operadores.poll());
		} else if(!supervisores.isEmpty()) {
			call.setEmployee(supervisores.poll());
		} else if(!directores.isEmpty()) {
			call.setEmployee(directores.poll());
		} else {
			Employee empleado = new Employee();
			Role role = new Role();
			empleado.setName("En espera...");
			role.setName("En espera...");
			empleado.setRole(role);
			call.setEmployee(empleado);
			call.setTimeCall(0);
			llamadasEnEspera.push(call);
		}
		
		return call;			
	}
	
	private void cargarEmpleados() {
		operadores = Lists.newLinkedList(employeeService.findByRoleAndFree(roleService.findOne(Long.parseLong(Cargo.OPERADOR.getValue())), Boolean.TRUE));
		supervisores = Lists.newLinkedList(employeeService.findByRoleAndFree(roleService.findOne(Long.parseLong(Cargo.SUPERVISOR.getValue())), Boolean.TRUE));
		directores = Lists.newLinkedList(employeeService.findByRoleAndFree(roleService.findOne(Long.parseLong(Cargo.DIRECTOR.getValue())), Boolean.TRUE));
	}
	
	private void reiniciar() {
		llamadaEntrante = 1;
		llamadasTotales = 0;
		conjuntoClientes.clear();
		clientesLlamando.clear();
		llamadasContestadas.clear();
		temporizador.cancel();
	}

}
