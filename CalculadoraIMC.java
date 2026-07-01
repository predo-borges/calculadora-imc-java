import javax.swing.*;
import java.awt.Color;
import helper_classes.*;

public class CalculadoraIMC {
  public static void main(String[] args) {

     JFrame frame = new JFrame("Calculadora IMC");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(951, 513);
     frame.setLocationRelativeTo(null);
     frame.setResizable(false);

     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#f4c064"));

     JTextField campoPeso = new JTextField("");
     campoPeso.setBounds(282, 168, 96, 40);
     campoPeso.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 30));
     campoPeso.setBackground(Color.decode("#ffe7bf"));
     campoPeso.setForeground(Color.decode("#73664e"));
     campoPeso.setBorder(new RoundedBorder(10, Color.decode("#000"), 1));
     OnFocusEventHelper.setOnFocusText(campoPeso, "Peso", Color.decode("#000"), Color.decode("#73664e"));
     panel.add(campoPeso);

     JLabel txt_Peso = new JLabel("Digite seu Peso:");
     txt_Peso.setBounds(42, 163, 235, 47);
     txt_Peso.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 30));
     txt_Peso.setForeground(Color.decode("#000"));
     panel.add(txt_Peso);

     JLabel Txt_Altura = new JLabel("Digite sua Altura: ");
     Txt_Altura.setBounds(44, 246, 230, 43);
     Txt_Altura.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 30));
     Txt_Altura.setForeground(Color.decode("#000"));
     panel.add(Txt_Altura);

     JTextField campoAltura = new JTextField("");
     campoAltura.setBounds(280, 246, 96, 40);
     campoAltura.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 30));
     campoAltura.setBackground(Color.decode("#ffe7bf"));
     campoAltura.setForeground(Color.decode("#73664e"));
     campoAltura.setBorder(new RoundedBorder(10, Color.decode("#000"), 1));
     OnFocusEventHelper.setOnFocusText(campoAltura, "Altura", Color.decode("#000"), Color.decode("#73664e"));
     panel.add(campoAltura);

     JButton botaoCalcular = new JButton("Calcular IMC");
     botaoCalcular.setBounds(72, 369, 295, 83);
     botaoCalcular.setBackground(Color.decode("#bca8e4"));
     botaoCalcular.setForeground(Color.decode("#000"));
     botaoCalcular.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 42));
     botaoCalcular.setBorder(new RoundedBorder(10, Color.decode("#3d364a"), 1));
     botaoCalcular.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(botaoCalcular, Color.decode("#7c6f97"), Color.decode("#bca8e4"));
     panel.add(botaoCalcular);

     JLabel element7 = new JLabel("Resultado do IMC");
     element7.setBounds(501, 13, 443, 73);
     element7.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 50));
     element7.setForeground(Color.decode("#000"));
     panel.add(element7);

     JLabel element8 = new JLabel("Calculadora IMC");
     element8.setBounds(26, -15, 430, 128);
     element8.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 50));
     element8.setForeground(Color.decode("#000"));
     panel.add(element8);

     JTextPane resultadoLabel = new JTextPane();
     resultadoLabel.setBounds(526, 107, 383, 356);
     resultadoLabel.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 30));
     resultadoLabel.setBackground(Color.decode("#ffe7bf"));
     resultadoLabel.setForeground(Color.decode("#000"));
     resultadoLabel.setBorder(new RoundedBorder(10, Color.decode("#000"), 1));
     resultadoLabel.setEditable(false);

     javax.swing.text.StyledDocument doc = resultadoLabel.getStyledDocument();
     javax.swing.text.SimpleAttributeSet center = new javax.swing.text.SimpleAttributeSet();
     javax.swing.text.StyleConstants.setAlignment(center, javax.swing.text.StyleConstants.ALIGN_CENTER);
     doc.setParagraphAttributes(0, doc.getLength(), center, false);

     panel.add(resultadoLabel);

     botaoCalcular.addActionListener(e -> {
        try {
           String textoPeso = campoPeso.getText().replace(",", ".").trim();
           String textoAltura = campoAltura.getText().replace(",", ".").trim();

           if (textoPeso.isEmpty() || textoPeso.equalsIgnoreCase("Peso") ||
               textoAltura.isEmpty() || textoAltura.equalsIgnoreCase("Altura")) {

              JOptionPane.showMessageDialog(
                 frame,
                 "Preencha os campos de PESO e ALTURA para calcular.",
                 "Campos vazios",
                 JOptionPane.WARNING_MESSAGE
              );
              return;
           }

           double peso = Double.parseDouble(textoPeso);
           double altura = Double.parseDouble(textoAltura);

           if (peso <= 0 || altura <= 0) {
              JOptionPane.showMessageDialog(
                 frame,
                 "Peso e altura devem ser maiores que zero.",
                 "Valores inválidos",
                 JOptionPane.WARNING_MESSAGE
              );
              return;
           }

           // LOGICA MATEMATICA

           double imc = peso / (altura * altura);

           double pesoIdealMinimo = 18.5 * (altura * altura);
           double pesoIdealMaximo = 24.9 * (altura * altura);

           String classificacao;

           if (imc < 18.5) {
              classificacao = "Abaixo do peso";
           } else if (imc < 25) {
              classificacao = "Peso normal";
           } else if (imc < 30) {
              classificacao = "Sobrepeso";
           } else if (imc < 35) {
              classificacao = "Obesidade grau I";
           } else if (imc < 40) {
              classificacao = "Obesidade grau II";
           } else {
              classificacao = "Obesidade grau III";
           }

           resultadoLabel.setText(
              String.format(
                 "Resultado:\n\nIMC: %.1f\n\nClassificação:\n%s\n\nPeso ideal para sua altura:\n%.1f kg a %.1f kg",
                 imc,
                 classificacao,
                 pesoIdealMinimo,
                 pesoIdealMaximo
              )
           );

           javax.swing.text.StyledDocument docResultado = resultadoLabel.getStyledDocument();
           javax.swing.text.SimpleAttributeSet centroResultado = new javax.swing.text.SimpleAttributeSet();
           javax.swing.text.StyleConstants.setAlignment(centroResultado, javax.swing.text.StyleConstants.ALIGN_CENTER);
           docResultado.setParagraphAttributes(0, docResultado.getLength(), centroResultado, false);

        } catch (NumberFormatException erro) {
           JOptionPane.showMessageDialog(
              frame,
              "Digite apenas números válidos.\n\nExemplo:\nPeso: 70.5\nAltura: 1.75",
              "Erro de entrada",
              JOptionPane.ERROR_MESSAGE
           );
        }
     });

     frame.add(panel);
     frame.setVisible(true);
  }
}