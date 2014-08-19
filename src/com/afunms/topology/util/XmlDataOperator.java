package com.afunms.topology.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.afunms.initialize.ResourceCenter;

@SuppressWarnings("unchecked")
public class XmlDataOperator {

	private FileOutputStream fos;

	private XMLOutputter serializer;

	private String fullPath;

	protected Document doc;

	protected Element root;

	protected Element nodes;

	protected Element lines;

	protected Element assistantLines;

	protected Element demoLines;// yangjun add ʾ����·

	public XmlDataOperator() {
	}

	public void setFile(String fileName) {
		fullPath = ResourceCenter.getInstance().getSysPath() + "linuxserver/" + fileName;
	}

	public void setfile(String fileName) {
		fullPath = ResourceCenter.getInstance().getSysPath() + "flex/data/" + fileName;
	}

	// ���ݱ�����������ͼlist���澯״̬���ظ�ͼ�Ľڵ�
	public void alarmNode(List alarmMapList) {
	}

	// �޸Ľڵ���Ϣ
	public void updateNode(String nodeId, String tag, String txt) {
		List eleNodes = nodes.getChildren();
		for (int i = 0; i < eleNodes.size(); i++) {
			Element ele = (Element) eleNodes.get(i);
			if (ele.getChildText("id").equals(nodeId)) {
				ele.getChild(tag).setText(txt);
				break;
			}
		}
	}

	/**
	 * ����xml�ļ�(��������ͼ�ϵ�"����"��ť)
	 */
	public void saveImage(String content) {
	}

	/**
	 * �����ļ�
	 */
	public void writeXml() {
		try {
			Format format = Format.getCompactFormat();
			format.setEncoding("GB2312");
			format.setIndent("	");
			serializer = new XMLOutputter(format);
			fos = new FileOutputStream(fullPath);
			serializer.output(doc, fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ׼������һ���µ�xml
	 */
	public void init4createXml() {
		root = new Element("root");
		nodes = new Element("interface");
	}

	/**
	 * ����һ���µ�xml
	 */
	public void createXml() {
		root.addContent(nodes);
		doc = new Document(root);
		writeXml();
	}

	/**
	 * ɾ��һ��xml
	 */
	public void deleteXml() {
		try {
			File delFile = new File(fullPath);
			delFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����һ���µĽڵ�
	 */
	public void addNode(String type, String subtype, String categroy, String entity, String subentity, String thevalue, String chname, String restype, String time, String unit,
			String bak) {
		Element eleNode = new Element("item");
		Element eleType = new Element("type");
		Element eleSubtype = new Element("subtype");
		Element eleCategory = new Element("category");
		Element eleEntity = new Element("entity");
		Element eleSubentity = new Element("subentity");
		Element eleThevalue = new Element("thevalue");
		Element eleChname = new Element("chname");
		Element eleRestype = new Element("restype");
		Element eleTime = new Element("time");
		Element eleUnit = new Element("unit");
		Element eleBak = new Element("bak");

		eleType.setText(type);
		eleSubtype.setText(subtype);
		eleCategory.setText(categroy);
		eleEntity.setText(entity);
		eleSubentity.setText(subentity);
		eleThevalue.setText(thevalue);
		eleChname.setText(chname);
		eleRestype.setText(restype);
		eleTime.setText(time);
		eleUnit.setText(unit);
		eleBak.setText(bak);

		eleNode.addContent(eleType);
		eleNode.addContent(eleSubtype);
		eleNode.addContent(eleCategory);
		eleNode.addContent(eleEntity);
		eleNode.addContent(eleSubentity);
		eleNode.addContent(eleThevalue);
		eleNode.addContent(eleChname);
		eleNode.addContent(eleRestype);
		eleNode.addContent(eleTime);
		eleNode.addContent(eleUnit);
		eleNode.addContent(eleBak);
		nodes.addContent(eleNode);
	}

	/**
	 * ����һ���µĽڵ�
	 */
	public void addIPNode(String ip, String type, String subtype, String categroy, String entity, String subentity, String thevalue, String chname, String restype, String time,
			String unit, String bak) {
		Element eleNode = new Element("item");
		Element eleIp = new Element("ip");
		Element eleType = new Element("type");
		Element eleSubtype = new Element("subtype");
		Element eleCategory = new Element("category");
		Element eleEntity = new Element("entity");
		Element eleSubentity = new Element("subentity");
		Element eleThevalue = new Element("thevalue");
		Element eleChname = new Element("chname");
		Element eleRestype = new Element("restype");
		Element eleTime = new Element("time");
		Element eleUnit = new Element("unit");
		Element eleBak = new Element("bak");

		eleIp.setText(ip);
		eleType.setText(type);
		eleSubtype.setText(subtype);
		eleCategory.setText(categroy);
		eleEntity.setText(entity);
		eleSubentity.setText(subentity);
		eleThevalue.setText(thevalue);
		eleChname.setText(chname);
		eleRestype.setText(restype);
		eleTime.setText(time);
		eleUnit.setText(unit);
		eleBak.setText(bak);

		eleNode.addContent(eleIp);
		eleNode.addContent(eleType);
		eleNode.addContent(eleSubtype);
		eleNode.addContent(eleCategory);
		eleNode.addContent(eleEntity);
		eleNode.addContent(eleSubentity);
		eleNode.addContent(eleThevalue);
		eleNode.addContent(eleChname);
		eleNode.addContent(eleRestype);
		eleNode.addContent(eleTime);
		eleNode.addContent(eleUnit);
		eleNode.addContent(eleBak);
		nodes.addContent(eleNode);
	}

	/**
	 * ����һ���µĽڵ�(fdb)
	 */
	public void addIPNode(String ip, String type, String subtype, String relateipaddr, String ifindex, String mac, String time, String ifband, String ifsms, String bak) {
		Element eleNode = new Element("item");
		Element eleIp = new Element("ip");
		Element eleType = new Element("type");
		Element eleSubtype = new Element("subtype");
		Element eleRelateipaddr = new Element("relateipaddr");
		Element eleIfindex = new Element("ifindex");
		Element eleMac = new Element("mac");

		Element eleTime = new Element("time");
		Element eleIfband = new Element("ifband");
		Element eleIfsms = new Element("ifsms");
		Element eleBak = new Element("bak");

		eleIp.setText(ip);
		eleType.setText(type);
		eleSubtype.setText(subtype);
		eleRelateipaddr.setText(relateipaddr);
		eleIfindex.setText(ifindex);
		eleMac.setText(mac);
		eleTime.setText(time);
		eleIfband.setText(ifband);
		eleIfsms.setText(ifsms);
		eleBak.setText(bak);

		eleNode.addContent(eleIp);
		eleNode.addContent(eleType);
		eleNode.addContent(eleSubtype);
		eleNode.addContent(eleRelateipaddr);
		eleNode.addContent(eleIfindex);
		eleNode.addContent(eleMac);

		eleNode.addContent(eleTime);
		eleNode.addContent(eleIfband);
		eleNode.addContent(eleIfsms);
		eleNode.addContent(eleBak);
		nodes.addContent(eleNode);
	}

	/**
	 * ����һ���µ�·�ɽڵ�
	 */
	public void addIPNode(String ip, String type, String subtype, String ifIndex, String nexthop, String proto, String iproutertype, String mask, String time, String physaddress,
			String dest) {
		Element eleNode = new Element("item");
		Element eleIp = new Element("ip");
		Element eleType = new Element("type");
		Element eleSubtype = new Element("subtype");
		Element eleIfIndex = new Element("ifIndex");
		Element eleNexthop = new Element("nexthop");
		Element eleProto = new Element("proto");
		Element eleIproutertype = new Element("iproutertype");
		Element eleMask = new Element("mask");
		Element eleTime = new Element("time");
		Element elePhysaddress = new Element("physaddress");
		Element eleDest = new Element("dest");

		eleIp.setText(ip);
		eleType.setText(type);
		eleSubtype.setText(subtype);
		eleIfIndex.setText(ifIndex);
		eleNexthop.setText(nexthop);
		eleProto.setText(proto);
		eleIproutertype.setText(iproutertype);
		eleMask.setText(mask);
		eleTime.setText(time);
		elePhysaddress.setText(physaddress);
		eleDest.setText(dest);

		eleNode.addContent(eleIp);
		eleNode.addContent(eleType);
		eleNode.addContent(eleSubtype);
		eleNode.addContent(eleIfIndex);
		eleNode.addContent(eleNexthop);
		eleNode.addContent(eleProto);
		eleNode.addContent(eleIproutertype);
		eleNode.addContent(eleMask);
		eleNode.addContent(eleTime);
		eleNode.addContent(elePhysaddress);
		eleNode.addContent(eleDest);
		nodes.addContent(eleNode);
	}

	/**
	 * ����һ���µ������ڵ�(���ڷ���֮�󣬻����ֶ�����һ�������ڵ�) yangjun add
	 */
	public void addHostNode(String nodeId, int categroy, String image, String ip, String alias, String x, String y) {
		Element eleNode = new Element("node");
		Element eleId = new Element("id");
		Element eleImg = new Element("img");
		Element eleX = new Element("x");
		Element eleY = new Element("y");
		Element eleIp = new Element("ip");
		Element eleAlias = new Element("alias");
		Element eleInfo = new Element("info");
		Element eleMenu = new Element("menu");
		Element relationMap = new Element("relationMap");// ��������ͼ

		eleId.setText(nodeId);
		eleId.setAttribute("category", NodeHelper.getNodeEnCategory(categroy));
		if (image == null)
			eleImg.setText(NodeHelper.getTopoImage(categroy));
		else
			eleImg.setText(image);
		eleX.setText(x);
		eleY.setText(y);
		eleIp.setText(ip);
		eleAlias.setText(alias);
		eleInfo.setText("�豸��ǩ:" + alias + "<br>IP��ַ:" + ip);
		eleMenu.setText(NodeHelper.getHostMenu(nodeId, ip, NodeHelper.getNodeEnCategory(categroy)));
		relationMap.setText("");
		eleNode.addContent(eleId);
		eleNode.addContent(eleImg);
		eleNode.addContent(eleX);
		eleNode.addContent(eleY);
		eleNode.addContent(eleIp);
		eleNode.addContent(eleAlias);
		eleNode.addContent(eleInfo);
		eleNode.addContent(eleMenu);
		eleNode.addContent(relationMap);
		nodes.addContent(eleNode);
	}

	/**
	 * ����һ����·
	 */
	public void addLine(String lineId, String startId, String endId) {
	}

	/**
	 * ����һ����· yangjun add
	 */
	public void addLine(String lineName, String lineId, String startId, String endId) {
	}

	/**
	 * ����һ��������·
	 */
	public void addAssistantLine(String lineId, String startId, String endId) {
	}

	/**
	 * ����һ��������· yangjun add
	 */
	public void addAssistantLine(String lineName, String lineId, String startId, String endId) {
	}

	/**
	 * ��xmlidɾ��һ�����
	 */
	public void deleteNodeByID(String nodeId) {
	}

	/**
	 * delete line whose startid or endid equals "nodeId"
	 */
	public void deleteLineByNodeID(String nodeId) {
	}

}