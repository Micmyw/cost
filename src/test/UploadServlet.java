package test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UploadServlet extends HttpServlet{
    public static final long serialVersionUID = 1L;

    // 上传文件存储目录
    public static final String  UPLOAD_DIRECTORY="upload";

    //上传配置.最小存储，最大文件大小，最大请求文件大小
    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     * @param request
     * @param response
     * @throws IOException
     */
    protected void dopost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            //如果不是则停止
            PrintWriter writer=response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        //配置上传参数
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置内存临界值，超过则上传临时目录
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        //中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/")+File.separator+UPLOAD_DIRECTORY;

        File uploadDir= new File(uploadPath);
        //如果目录不存在则创建
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // 解析请求的内容提取文件数据
        List<FileItem> formitems = upload.parseRequest(request);

        if (formitems!=null && formitems.size()>0) {
            //迭代表单数据
            for (FileItem item:formitems) {
                //处理不在表单中的字段
                if (item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator+fileName;
                    File storeFile = new File(filePath);
                    //输出文件路径
                    System.out.println(filePath);
                    //保存文件到硬盘
                    item.write(storeFile);
                    request.setAttribute("message","文件上传成功");

                }
            }
        }
        //跳转到message.jsp
        getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
    }
}
