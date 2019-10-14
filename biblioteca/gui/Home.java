package biblioteca.gui;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.border.EmptyBorder;
import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.livroDAO;
import biblioteca.gui.Templates.TemplateHome;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JButton;

public class Home extends JFrame {

	private TemplateHome contentPane;
	private JMenuBar menuBar;
	private JMenu mnUsuario,mnLivro,mnEmprestimo,mnSobre;
	private JMenuItem mntmCadastrar ;
	private JMenuItem mntmPesquisar_1;
	private JMenuItem mntmAlterar;
	private JMenuItem mntmDesativar;
	private JMenuItem mntmAtivar;
	private JMenuItem mntmListar;
	private JMenuItem mntmCadastrarLivro;
	private JMenuItem mntmPesquisar;
	private JMenuItem mntmAlterarLivro;
	private JMenuItem mntmListarLivro;
	private JMenuItem mntmAtivar_1;
	private JMenuItem mntmAlterarQuantidade;
	private JMenuItem mntmDesativar_1;
	private JMenuItem mntmCadastrar_1;
	private JMenuItem mntmLista;
	private JMenuItem mntmEncerrar;
	private JMenuItem mntmDesenvolvimento;
	
	public Home() {
		setTitle("SISTEMA BIBLIOTECARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		File f = new File("//media//tiago//Dados//eclipse-workspace//tese-1//src//biblioteca//img//livros2.png");
		try {
			Templates.FundoBg(f);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("imagem de fundo nao encontrada");
		}
		contentPane = new TemplateHome();
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroUsuario cadUser = new CadastroUsuario();
			}
		});
		
		mntmPesquisar_1 = new JMenuItem("Pesquisar");
		mntmPesquisar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarUsuario pu = new PesquisarUsuario(mntmPesquisar_1.getText()+" ");
				pu.setVisible(true);
			}
		});
		mnUsuario.add(mntmPesquisar_1);
		mnUsuario.add(mntmCadastrar);
		
		mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarUsuario pu = new PesquisarUsuario(mntmAlterar.getText());
				pu.setVisible(true);
			}
		});
		
		mntmDesativar = new JMenuItem("Desativar");
		mntmDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarUsuario pu = new PesquisarUsuario(mntmDesativar.getText());
				pu.setVisible(true);
			}
		});
		mnUsuario.add(mntmDesativar);
		
		mntmAtivar = new JMenuItem("Ativar");
		mntmAtivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarUsuario pu = new PesquisarUsuario(mntmAtivar.getText());
				pu.btnLista.setVisible(false);
				pu.setVisible(true);
			}
		});
		mnUsuario.add(mntmAtivar);
		mnUsuario.add(mntmAlterar);
		
		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaDinamicaUsuario tdu = new TabelaDinamicaUsuario("*");
				tdu.btnNovo.setEnabled(true);
				tdu.btnDesativar.setEnabled(true);
				tdu.btnAlterar.setEnabled(true);
				tdu.setVisible(true);
			}
		});
		mnUsuario.add(mntmListar);
		
		mnLivro = new JMenu("Livro");
		menuBar.add(mnLivro);
		
		mntmCadastrarLivro = new JMenuItem("Cadastrar");
		mntmCadastrarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				CadastrarLivro cl = new CadastrarLivro(ldao.IdMax()+1);
			}
		});
		
		mntmPesquisar = new JMenuItem("Pesquisar");
		mntmPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarLivro pl = new PesquisarLivro(mntmPesquisar.getText());
				pl.setVisible(true);
			}
		});
		mnLivro.add(mntmPesquisar);
		mnLivro.add(mntmCadastrarLivro);
		
		mntmAlterarLivro = new JMenuItem("Alterar");
		mntmAlterarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarLivro pl = new PesquisarLivro(mntmAlterarLivro.getText());
				pl.setVisible(true);
			}
		});
		mnLivro.add(mntmAlterarLivro);
		
		mntmListarLivro = new JMenuItem("Listar");
		mntmListarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TabelaDinamicaLivro tdl = new TabelaDinamicaLivro("*",-1);
				tdl.btnNovo.setEnabled(true);
				tdl.btnExcluir.setEnabled(true);
				tdl.btnAlterar.setEnabled(true);
				tdl.setVisible(true);
			}
		});
		
		mntmAtivar_1 = new JMenuItem("Ativar");
		mntmAtivar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarLivro pl = new PesquisarLivro(mntmAtivar_1.getText());
				pl.setVisible(true);
			}
		});
		
		mntmAlterarQuantidade = new JMenuItem("Alterar Quantidade");
		mntmAlterarQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarQuantidadeLivro aql = new AlterarQuantidadeLivro(mntmAlterarQuantidade.getText());
				aql.setVisible(true);
			}
		});
		mnLivro.add(mntmAlterarQuantidade);
		mnLivro.add(mntmAtivar_1);
		
		mntmDesativar_1 = new JMenuItem("Desativar");
		mntmDesativar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarLivro pl = new PesquisarLivro(mntmDesativar_1.getText());
				pl.setVisible(true);
			}
		});
		mnLivro.add(mntmDesativar_1);
		mnLivro.add(mntmListarLivro);
		
		mnEmprestimo = new JMenu("Emprestimo");
		menuBar.add(mnEmprestimo);
		
		mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Inte i = new Inte();
				CadastroEmprestimo ce = new CadastroEmprestimo();
				ce.setVisible(true);
			}
		});
		mnEmprestimo.add(mntmCadastrar_1);
		
		mntmLista = new JMenuItem("Lista");
		mntmLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TabelaDinamicaEmprestimo tde = new TabelaDinamicaEmprestimo("*",-1);
				tde.btnNovo.setEnabled(true);
				tde.btnEncerrar.setEnabled(true);
				tde.setVisible(true);
			}
		});
		
		mntmEncerrar = new JMenuItem("Encerrar");
		mntmEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisarEmprestimo pe =new PesquisarEmprestimo(mntmEncerrar.getText());
				pe.setVisible(true);
			}
		});
		mnEmprestimo.add(mntmEncerrar);
		mnEmprestimo.add(mntmLista);
		
		mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		mntmDesenvolvimento = new JMenuItem("Desenvolvimento");
		mntmDesenvolvimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("//~//Desktop//eclipse-workspace//tese-1//src//biblioteca//text//leia.txt");
				FileReader fr;
				BufferedReader br;
				List<String> lista = null;
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);
					lista = new ArrayList<>();
					while(br.ready())
						lista.add(br.readLine());
				} catch (Exception e) {
					// TODO: handle exception
				}
//				String vet[] = new String[lista.size()];
//				for(int i=0;i<lista.size();i++)
//					vet[i] = lista.get(i);
				JOptionPane.showMessageDialog(null, lista);
			}
		});
		mnSobre.add(mntmDesenvolvimento);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1341, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 691, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		pack();
	}
}
