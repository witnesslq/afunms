package com.afunms.emc.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.afunms.common.base.BaseDao;
import com.afunms.common.base.BaseVo;
import com.afunms.common.base.DaoInterface;
import com.afunms.emc.model.Agent;
import com.afunms.emc.model.Disk;

@SuppressWarnings("unchecked")
public class diskPerDao extends BaseDao implements DaoInterface {

	public void delete(String nodeid) {
		try {
			conn.executeUpdate("delete from nms_emcdiskcon where nodeid ='" + nodeid + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	// ����vid�������������Ϣ
	public List<HashMap<String, Object>> getbyvid(List vid, String tablename, String ipaddress) {
		String ip = ipaddress.replace(".", "_");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < vid.size(); i++) {
			String queryonesql = "select * from " + tablename + ip + " where vid='" + vid.get(i) + "'";
			HashMap map = new HashMap();
			try {
				rs = conn.executeQuery(queryonesql);
				while (rs.next()) {
					// �����������Ϣ
					if (tablename.equalsIgnoreCase("vm_basevmware")) {
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String vmpower = rs.getString("vmpower");
						map.put("powerstate", vmpower);
						String fullname = rs.getString("fullname");
						map.put("guestfullname", fullname);
						String cpu = rs.getString("cpu");
						map.put("cpu", cpu);
						String memoryuse = rs.getString("memoryuse");
						map.put("memorysizemb", memoryuse);
						String hoid = rs.getString("hoid");
						map.put("hoid", hoid);
					} else if (tablename.equalsIgnoreCase("vm_basephysical")) {// ������������Ϣ
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String hostpower = rs.getString("hostpower");
						map.put("powerstate", hostpower);
						String model = rs.getString("model");
						map.put("model", model);
						String cpunum = rs.getString("cpunum");
						map.put("numcore", cpunum);
						String netnum = rs.getString("netnum");
						map.put("numnics", netnum);
						String memory = rs.getString("memory");
						map.put("memorysizemb", memory);
						String ghz = rs.getString("ghz");
						map.put("cpumhz", ghz);
					} else if (tablename.equalsIgnoreCase("vm_baseyun")) {// ����Դ������Ϣ
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String disk = rs.getString("disk");
						map.put("totaldssizemb", disk);
						String cpuuse = rs.getString("cpuuse");
						map.put("totalcpu", cpuuse);
						String hostnum = rs.getString("hostnum");
						map.put("numhosts", hostnum);
						String mem = rs.getString("mem");
						map.put("totalmemory", mem);
						String cpunum = rs.getString("cpunum");
						map.put("numcpucores", cpunum);
					} else if (tablename.equalsIgnoreCase("vm_basedatastore")) {// �洢������Ϣ
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String store = rs.getString("store");
						map.put("capacity", store);
						String unusedstore = rs.getString("unusedstore");
						map.put("freespace", unusedstore);
					} else if (tablename.equalsIgnoreCase("vm_basedatacenter")) {// �������Ļ�����Ϣ
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String dcid = rs.getString("dcid");
						map.put("dcid", dcid);
					} else if (tablename.equalsIgnoreCase("vm_baseresource")) {// ��Դ�ػ�����Ϣ
						String id = rs.getString("vid");
						map.put("vid", id);
						String name = rs.getString("name");
						map.put("name", name);
						String dcid = rs.getString("dcid");
						map.put("dcid", dcid);
						String crid = rs.getString("crid");
						map.put("crid", crid);
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			list.add(map);
		}
		return list;
	}

	public BaseVo loadFromRS(ResultSet rs) {
		Disk disk = new Disk();
		try {
			disk.setSerialNumber(rs.getString("serialnumber"));
			disk.setNumberofReads(rs.getString("numberofreads"));
			disk.setNumberofWrites(rs.getString("numberofwrites"));
			disk.setKbytesRead(rs.getString("kbytesread"));
			disk.setKbytesWritten(rs.getString("kbyteswritten"));
			disk.setSoftReadErrors(rs.getString("softreaderrors"));
			disk.setSoftWriteErrors(rs.getString("softwriteerrors"));
			disk.setIdleTicks(rs.getString("idleticks"));
			disk.setBusyTicks(rs.getString("busyticks"));
			disk.setHardReadErrors(rs.getString("hardreaderrors"));
			disk.setHardWriteErrors(rs.getString("hardwriteerrors"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			disk = null;
		}
		return disk;
	}

	public List<Disk> query(String nodeid) {
		List<Disk> list = new ArrayList<Disk>();
		String queryonesql = "select * from nms_emcdiskcon where nodeid='" + nodeid + "'";
		try {
			rs = conn.executeQuery(queryonesql);
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				list.add((Disk) loadFromRS(rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	// ȡ���ܱ�������һ����¼
	public HashMap queryLast(String tablename, String ipp, String vid) {
		HashMap map = new HashMap();
		String sql = "";
		String ip = ipp.replace(".", "_");
		if (tablename.equalsIgnoreCase("vm_host")) {
			sql = "select cpu,cpuuse,mem,memin,memout,disk,meminc from vm_host" + ip + " where hostid='" + vid + "' group by collecttime desc";
			try {
				rs = conn.executeQuery(sql);
				if (rs.next()) {
					map.put("cpu", rs.getString("cpu"));
					map.put("cpuuse", rs.getString("cpuuse"));
					map.put("mem", rs.getString("mem"));
					map.put("memin", rs.getString("mem"));
					map.put("memout", rs.getString("memout"));
					map.put("disk", rs.getString("disk"));
					map.put("meminc", rs.getString("meminc"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (tablename.equalsIgnoreCase("vm_guesthost")) {
			sql = "select cpu,cpuuse,mem,memin,memout,disk,meminc,net from vm_guesthost" + ip + " where vid='" + vid + "' group by collecttime desc";
			try {
				rs = conn.executeQuery(sql);
				if (rs.next()) {
					map.put("cpu", rs.getString("cpu"));
					map.put("cpuuse", rs.getString("cpuuse"));
					map.put("mem", rs.getString("mem"));
					map.put("memin", rs.getString("mem"));
					map.put("memout", rs.getString("memout"));
					map.put("disk", rs.getString("disk"));
					map.put("meminc", rs.getString("meminc"));
					map.put("net", rs.getString("net"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (tablename.equalsIgnoreCase("vm_cluster")) {
			sql = "select cpu,mem from vm_cluster" + ip + " where vid='" + vid + "' group by collecttime desc";
			try {
				rs = conn.executeQuery(sql);
				if (rs.next()) {
					map.put("cpu", rs.getString("cpu"));
					map.put("mem", rs.getString("mem"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (tablename.equalsIgnoreCase("vm_resourcepool")) {
			sql = "select cpu from vm_resourcepool" + ip + " where vid='" + vid + "' group by collecttime desc";
			try {
				rs = conn.executeQuery(sql);
				if (rs.next()) {
					map.put("cpu", rs.getString("cpu"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return map;
	}

	public String querySize(String nodeid, String category) {
		String num = "";
		String queryonesql = "select count(*) as num from nms_vmwarevid where category='" + category + "'  and nodeid=" + nodeid;
		try {
			rs = conn.executeQuery(queryonesql);
			while (rs.next()) {
				num = rs.getString("num");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}

	public boolean save(BaseVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean save(BaseVo vo, String nodeid) {
		Agent agent = (Agent) vo;
		StringBuffer addsql = new StringBuffer(200);
		addsql.append("insert into nms_emcsystem(nodeid,name,node,agentrev,cabinet,descr,model,modeltype,"
				+ "peersignature,physicalnode,promrev,revision,scsiid,serialno,signature,spidentifier,spmemory)values('");
		addsql.append(nodeid);
		addsql.append("','");
		addsql.append(agent.getName());
		addsql.append("','");
		addsql.append(agent.getNode());
		addsql.append("','");
		addsql.append(agent.getAgentRev());
		addsql.append("','");
		addsql.append(agent.getCabinet());
		addsql.append("','");
		addsql.append(agent.getDescr());
		addsql.append("','");
		addsql.append(agent.getModel());
		addsql.append("','");
		addsql.append(agent.getModelType());
		addsql.append("','");
		addsql.append(agent.getPeerSignature());
		addsql.append("','");
		addsql.append(agent.getPhysicalNode());
		addsql.append("','");
		addsql.append(agent.getPromRev());
		addsql.append("','");
		addsql.append(agent.getRevision());
		addsql.append("','");
		addsql.append(agent.getSCSIId());
		addsql.append("','");
		addsql.append(agent.getSerialNo());
		addsql.append("','");
		addsql.append(agent.getSignature());
		addsql.append("','");
		addsql.append(agent.getSPIdentifier());
		addsql.append("','");
		addsql.append(agent.getSPMemory());
		addsql.append("')");
		System.out.println(addsql.toString());
		return saveOrUpdate(addsql.toString());
	}

	public boolean save(List sql) {
		// TODO Auto-generated method stub
		if (sql != null && sql.size() > 0) {
			try {
				for (int i = 0; i < sql.size(); i++) {
					conn.addBatch(sql.get(i) + "");
				}
				conn.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		}
		return true;
	}

	public boolean save(String sql) {
		return saveOrUpdate(sql);
	}

	public boolean saveList(List<Disk> list, String ip) {

		if (ip.contains(".")) {
			ip = ip.replace(".", "_");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date cc = new Date();
		String time = sdf.format(cc);

		if (list != null && list.size() > 0) {
			try {
				StringBuffer addsql = new StringBuffer(100);
				for (int i = 0; i < list.size(); i++) {
					addsql = new StringBuffer(100);
					Disk disk = (Disk) list.get(i);
					if (disk.getSerialNumber() == null || "null".equalsIgnoreCase(disk.getSerialNumber())) {
						continue;
					}
					addsql.append("insert into emcdiskper" + ip + "(serialnumber,numberofreads,numberofwrites,softreaderrors,softwriteerrors,kbytesread,kbyteswritten,idleticks,"
							+ "busyticks,hardreaderrors,hardwritererrors,collecttime)values('");
					addsql.append(disk.getSerialNumber());
					addsql.append("','");
					addsql.append(disk.getNumberofReads());
					addsql.append("','");
					addsql.append(disk.getNumberofWrites());
					addsql.append("','");
					addsql.append(disk.getSoftReadErrors());
					addsql.append("','");
					addsql.append(disk.getSoftWriteErrors());
					addsql.append("','");
					addsql.append(disk.getKbytesRead());
					addsql.append("','");
					addsql.append(disk.getKbytesWritten());
					addsql.append("','");
					addsql.append(disk.getIdleTicks());
					addsql.append("','");
					addsql.append(disk.getBusyTicks());
					addsql.append("','");
					addsql.append(disk.getHardReadErrors());
					addsql.append("','");
					addsql.append(disk.getHardWriteErrors());
					addsql.append("','");
					addsql.append(time);
					addsql.append("')");
					conn.addBatch(addsql.toString());
				}
				conn.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn.close();
			}
		}
		return true;
	}

	public boolean update(BaseVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

}