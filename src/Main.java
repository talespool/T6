import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "informe dois arquivos de entrada. Ex.: java Main ../dados/arvore1.txt ../dados/arvore2.txt"
            );
        }

        Graph tree1 = new Graph(new In(args[0]));
        Graph tree2 = new Graph(new In(args[1]));

        StdOut.println("Arvore 1:");
        StdOut.println(tree1);
        StdOut.println();

        StdOut.println("Arvore 2:");
        StdOut.println(tree2);
        StdOut.println();

        TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
        TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

        if (!analysis1.isTree()) {
            StdOut.println("Falha na Arvore 1: " + analysis1.getValidationMessage());
            return;
        }
        if (!analysis2.isTree()) {
            StdOut.println("Falha na Arvore 2: " + analysis2.getValidationMessage());
            return;
        }
        
        StdOut.println("Validacao: Ambas as entradas sao arvores validas.");
        StdOut.println();

        int[] centers1 = analysis1.getCenters();
        int[] centers2 = analysis2.getCenters();
        StdOut.println("Centros da Arvore 1: " + Arrays.toString(centers1));
        StdOut.println("Centros da Arvore 2: " + Arrays.toString(centers2));
        StdOut.println();

        String code1 = analysis1.getCanonicalEncoding();
        String code2 = analysis2.getCanonicalEncoding();
        StdOut.println("Codificacao Arvore 1: " + code1);
        StdOut.println("Codificacao Arvore 2: " + code2);
        StdOut.println();

        if (analysis1.isIsomorphicTo(analysis2)) {
            StdOut.println("VEREDITO: AS ARVORES SAO ISOMORFAS");
        } else {
            StdOut.println("VEREDITO: AS ARVORES NAO SAO ISOMORFAS");
        }
    }
}