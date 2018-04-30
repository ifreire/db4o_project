package br.com.ifreire.controllers;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;

import br.com.ifreire.models.Agenda;
import br.com.ifreire.models.contato.Fone;

public class ReportsController
{
	private JasperReport gerarJasperReport(String nomeRelatorio)
	{
		JasperReport jReport = null;
		try
		{
			jReport = JasperCompileManager.compileReport(ReportsController.class.getResourceAsStream(nomeRelatorio)); //"report.jrxml"
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
		
		return jReport;
	}
	
	public void gerarRelatorioAgenda(List<Agenda> agendaList, List<Fone> foneList, String nomeRelatorio) throws Exception
	{
		JasperReport report = gerarJasperReport(nomeRelatorio);
		
		try
		{
            for (Agenda agenda : agendaList)
			{
				for (Fone fone : foneList)
				{
					if (agenda.getId().equals(fone.getId()))
					{
						agenda.setFoneList(foneList);
						break;
					}
				}
			}
			
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(agendaList);
            final JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "JasperReport.pdf");
        }
		catch (Exception ex)
		{
			throw(ex);
		}
	}
}