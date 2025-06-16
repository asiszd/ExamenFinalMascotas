package com.example.Cliente.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Cliente.DAO.IClienteDAO;
import com.example.Cliente.Dominio.Cliente;
import com.example.Cliente.Entidad.Mascota;
import com.example.Cliente.FeignClients.IMascotaFeign;

@Service
public class ClienteServiceImp implements IClienteService{
	
	@Autowired
	private IClienteDAO dao;
	
	@Autowired
	private IMascotaFeign mascotaFC;

	@Override
	public Cliente guardar(Cliente c) {
		return dao.save(c);
	}

	@Override
	public List<Cliente> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idCliente"));
	}

	@Override
	public Cliente buscar(int idCliente) {
		return dao.findById(idCliente).orElse(null);
	}

	@Override
	public void eliminar(int idCliente) {
		dao.deleteById(idCliente);
	}
	
	public HashMap<String, Object> listaMascotas(int clienteId){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		Cliente existe = dao.findById(clienteId).orElse(null);
		if(existe != null) {
			hash.put("cliente", existe);
			List<Mascota> mascotas = mascotaFC.buscarPorClienteId(clienteId);
			if(mascotas.isEmpty()) {
				hash.put("mensaje", "No tiene mascotas registradas");
			} else {
				hash.put("mascotas", mascotas);
			}
		}
		return hash;
	}

}
