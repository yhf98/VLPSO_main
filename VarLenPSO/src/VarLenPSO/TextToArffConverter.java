package VarLenPSO;

import java.io.*;

public class TextToArffConverter {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String inputFilePath = currentDir + "\\Data\\SRBCT_GEMS.txt";
        String outputFilePath = currentDir + "\\Data\\data.arff";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            FileWriter writer = new FileWriter(outputFilePath);

            // 写入 ARFF 文件头部信息
            writer.write("@relation SRBCT_GEMS.txt\n\n");

            // 解析文本文件的头部行，将每个字段作为 ARFF 属性写入文件
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");
            for (String header : headers) {
                writer.write("@attribute " + header.trim() + " numeric\n");
            }
            writer.write("\n@data\n");

            // 解析文本文件的数据行，将每一行的值写入 ARFF 文件
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.trim() + "\n");
            }

            reader.close();
            writer.close();

            System.out.println("转换成功！生成的 ARFF 文件路径：" + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
