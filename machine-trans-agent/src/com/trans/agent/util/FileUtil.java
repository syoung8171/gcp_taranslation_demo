
package com.trans.agent.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The Class FileUtil.
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 *
 * ---------------  ----------------  ---------------------------
 * 2015. 1. 15
 *
 * </pre>
 * @since 2015. 1. 15
 */

public class FileUtil
{

    /**
	 * Read file string.
	 *
	 * @param fileName
	 *            the file name
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
     public static String readFileString( String fileName ) throws IOException
     {
        return readFileString( new File( fileName ) );
     }

    /**
	 * Read file string.
	 *
	 * @param file
	 *            the file
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
     public static String readFileString( File file ) throws IOException
     {
        return new String( readFileBytes( file ) );
     }

    /**
	 * Read file bytes.
	 *
	 * @param fileName
	 *            the file name
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
     public static byte[] readFileBytes( String fileName ) throws IOException
     {
        return readFileBytes( new File( fileName ) );
     }

     /**
		 * Checks if is exist.
		 *
		 * @param path
		 *            the path
		 * @return true, if is exist
		 */
     public static boolean isExist(String path) {
    	java.io.File dir = new java.io.File(path);
		return dir.exists();
     }

     /**
		 * Check make.
		 *
		 * @param path
		 *            the path
		 * @return true, if successful
		 */
     public static boolean checkMake(String path) {
    	 boolean result = FileUtil.isExist(path);
    	 if (!result) {
    		 java.io.File newDir = new java.io.File(path);
    		 newDir.mkdir();

    		 return true;
    	 }
    	 return false;
     }

    /**
	 * Read file bytes.
	 *
	 * @param file
	 *            the file
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
     public static byte[] readFileBytes( File file ) throws IOException
     {
        FileInputStream fis = null;
        try
        {
            if( !file.exists() ) return null;

            byte[] fileBytes = new byte[ (int)file.length() ];
            fis = new FileInputStream( file );
            BufferedInputStream bis = new BufferedInputStream( fis );

            bis.read( fileBytes );

            return fileBytes;
        }
        catch( IOException ioe )
        {
            throw ioe;
        }
        catch( Exception ioe )
        {
            return null;
        }
        finally
        {
            if( fis != null )
            {
                try
                {
                    fis.close();
                }
                catch( IOException ioe ) {}
            }
        }
     }

    /**
	 * Gets the absolute path.
	 *
	 * @param fileName
	 *            the file name
	 * @return the absolute path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
     public static String getAbsolutePath( String fileName ) throws IOException
     {
        return getAbsolutePath( new File( fileName ) );
     }

    /**
	 * Gets the absolute path.
	 *
	 * @param file
	 *            the file
	 * @return the absolute path
	 */
     public static String getAbsolutePath( File file )
     {
        return StringUtil.inst().replace( file.getAbsolutePath(), "\\", "/" );
     }

    /**
	 * Find files.
	 *
	 * @param fileName
	 *            the file name
	 * @param filterName
	 *            the filter name
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findFiles( String fileName, String filterName ) throws IOException
    {
        return findFiles( fileName, filterName, false );
    }

    /**
	 * Find files.
	 *
	 * @param fileName
	 *            the file name
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findFiles( String fileName, String filterName, boolean recursive ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File name not set." );
        }

        if( filterName == null )
        {
            throw new IOException( "Filter name not set." );
        }

        return findFiles( new File( fileName ), newFileFilter( filterName ), recursive );
    }

    /**
	 * Find files.
	 *
	 * @param file
	 *            the file
	 * @param filter
	 *            the filter
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findFiles( File file, FileFilter filter ) throws IOException
    {
        return findFiles( file, filter, false );
    }

	 /**
	 * Find files.
	 *
	 * @param file
	 *            the file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findFiles( File file, FileFilter filter, boolean recursive ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File not set." );
        }

        if( filter == null )
        {
            throw new IOException( "Filter not set." );
        }

        return findFiles( new ArrayList<File>(), file, filter, recursive, true );
    }

    /**
	 * Find files.
	 *
	 * @param list
	 *            the list
	 * @param file
	 *            the file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static List<File> findFiles( List<File> list, File file, FileFilter filter, boolean recursive, boolean first ) throws IOException
    {
        if( filter != null && !filter.accept( file ) )
        {
            return list;
        }

        if( file.isFile() )
        {
            list.add( file );
        }
        else
        if( first || recursive )
        {
            File[] files = file.listFiles();
            for( int index = 0; files != null && index < files.length; index++ )
            {
                findFiles( list, files[index], filter, recursive, false );
            }
        }

        return list;
    }

	 /**
	 * Find directories.
	 *
	 * @param directoryName
	 *            the directory name
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findDirectories( String directoryName ) throws IOException
    {
        return findDirectories( directoryName, false );
    }

	 /**
	 * Find directories.
	 *
	 * @param directoryName
	 *            the directory name
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findDirectories( String directoryName, boolean recursive ) throws IOException
    {
        if( directoryName == null )
        {
            throw new IOException( "Directory name is not set." );
        }

        return findDirectories( new File( directoryName ), recursive );
    }

	 /**
	 * Find directories.
	 *
	 * @param directory
	 *            the directory
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findDirectories( File directory ) throws IOException
    {
        return findDirectories( directory, false );
    }

	 /**
	 * Find directories.
	 *
	 * @param directory
	 *            the directory
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findDirectories( File directory, boolean recursive ) throws IOException
    {
        if( directory == null )
        {
            throw new IOException( "Directory is null." );
        }

        if( directory.isFile() )
        {
            throw new IOException( "The directory " + directory.getPath() + " cannot be a file." );
        }

        return findDirectories( new ArrayList<File>(), directory, recursive, true );
    }

    /**
	 * Find directories.
	 *
	 * @param list
	 *            the list
	 * @param directory
	 *            the directory
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static List<File> findDirectories( List<File> list, File directory, boolean recursive, boolean first ) throws IOException
    {
        list.add( directory );

        if( first || recursive )
        {
            File[] directories = directory.listFiles();
            for( int index = 0; directories != null && index < directories.length; index++ )
            {
                if( directories[index].isDirectory() )
                {
                    findDirectories( list, directories[index], recursive, false );
                }
            }
        }

        return list;
    }

    /**
	 * Find in files.
	 *
	 * @param fileName
	 *            the file name
	 * @param sourceString
	 *            the source string
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findInFiles( String fileName, String sourceString ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File name not set." );
        }

        return findInFiles( new File( fileName ), sourceString, null, false );
    }

    /**
	 * Find in files.
	 *
	 * @param fileName
	 *            the file name
	 * @param sourceString
	 *            the source string
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findInFiles( String fileName, String sourceString, String filterName, boolean recursive ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File name not set." );
        }

        return findInFiles( new File( fileName ), sourceString, newFileFilter(filterName), recursive );
    }

    /**
	 * Find in files.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findInFiles( File file, String sourceString ) throws IOException
    {
        return findInFiles( file, sourceString, null, false );
    }

    /**
	 * Find in files.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static List<File> findInFiles( File file, String sourceString, FileFilter filter, boolean recursive ) throws IOException
    {
        return findInFiles( new ArrayList<File>(), file, sourceString, filter, recursive, true );
    }

    /**
	 * Find in files.
	 *
	 * @param list
	 *            the list
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static List<File> findInFiles( List<File> list, File file, String sourceString, FileFilter filter, boolean recursive, boolean first ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File not set." );
        }

        if( !file.exists() )
        {
            throw new IOException( "File " + file.getName() + " does not exist." );
        }

        if( sourceString == null )
        {
            throw new IOException( "Source String not set." );
        }

        if( sourceString.length() == 0 )
        {
            throw new IOException( "Source String has no length." );
        }

        if( filter != null && !filter.accept( file ) ) return list;

        if( file.isFile() && containsInFile( file, sourceString ) )
        {
            list.add( file );
        }
        else
        if( first || recursive )
        {
            File[] files = file.listFiles();
            for( int index = 0; files != null && index < files.length; index++ )
            {
                findInFiles( list, files[index], sourceString, filter, recursive, false );
            }
        }

        return list;
    }

    /**
     * @param path
     * @param filename
     * @return
     * @throws IOException
     *
     * path is null, that is window
     */
    public static String findFileSymbolicName(String path, String filename) throws IOException {
    	if(path == null)
    		return filename;

    	File filepaths = new File(path);
    	if(filepaths == null) {
    		throw new IOException("path not found");
    	}
    	for(File filepath : filepaths.listFiles()) {
    		if(filepath.getName().contains(filename) == false) {
    			continue;
    		}
    		String symbol = Files.readSymbolicLink(filepath.toPath()).toString();
    		if(symbol == null) {
    			throw new IOException("file not found");
    		}
			return symbol.substring(symbol.lastIndexOf("/")+1, symbol.length());
    	}
    	return null;
    }


    /**
	 * Contains in file.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static boolean containsInFile( File file, String sourceString ) throws IOException
    {
        byte[] bytes = sourceString.getBytes();

        int foundLength = 0;
        byte b = -1;

        BufferedInputStream bis = null;

        try
        {
            FileInputStream fis = new FileInputStream( file );
            bis = new BufferedInputStream( fis );
            while( ( b = (byte)bis.read() ) != -1 )
            {
                if( b == bytes[foundLength] )
                {
                    foundLength++;
                    if( foundLength == bytes.length )
                    {
                        bis.close();
                        return true;
                    }
                }
                else
                {
                    foundLength = 0 ;
                }
            }
        }
        finally
        {
           if( bis != null ) bis.close();
        }

        return false;
    }

    /**
	 * Replace.
	 *
	 * @param fileName
	 *            the file name
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static int replace( String fileName, String sourceString, String targetString ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File name not set." );
        }

        return replace( new File( fileName ), sourceString, targetString, null, false );
    }

    /**
	 * Replace.
	 *
	 * @param fileName
	 *            the file name
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static int replace( String fileName, String sourceString, String targetString, String filterName, boolean recursive ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File name not set." );
        }

        return replace( new File( fileName ), sourceString, targetString, newFileFilter(filterName), recursive );
    }

    /**
	 * Replace.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static int replace( File file, String sourceString, String targetString ) throws IOException
    {
        return replace( file, sourceString, targetString, null, false );
    }

    /**
	 * Replace.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static int replace( File file, String sourceString, String targetString, FileFilter filter, boolean recursive ) throws IOException
    {
        return replace( file, sourceString, targetString, filter, recursive, true );
    }

    /**
	 * Replace.
	 *
	 * @param file
	 *            the file
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static int replace( File file, String sourceString, String targetString, FileFilter filter, boolean recursive, boolean first ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File not set." );
        }

        if( !file.exists() )
        {
            throw new IOException( "File " + file.getPath()+ " does not exist." );
        }

        if( sourceString == null )
        {
            throw new IOException( "Source String not set." );
        }

        if( sourceString.length() == 0 )
        {
            throw new IOException( "Source String has no length." );
        }

        if( targetString == null )
        {
            throw new IOException( "Target String not set." );
        }

        int count = 0;

        if( file.isFile() )
        {
            return replaceInFile( file, sourceString, targetString );
        }
        else
        if( first || recursive )
        {
            File[] files = file.listFiles();
            for( int index = 0; files != null && index < files.length; index++ )
            {
                count += replace( files[index], sourceString, targetString, filter, recursive );
            }
        }


        return count;
    }

    /**
	 * Replace in file.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param sourceString
	 *            the source string
	 * @param targetString
	 *            the target string
	 * @return the int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static int replaceInFile( File sourceFile, String sourceString, String targetString ) throws IOException
    {
        byte[] sourceBytes = sourceString.getBytes();
        byte[] targetBytes = targetString.getBytes();

        int foundLength = 0;
        byte b = -1;
        int count = 0;

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File tempFile = File.createTempFile( sourceFile.getName(), "tmp" );

        try
        {
            FileInputStream fis = new FileInputStream( sourceFile );
            bis = new BufferedInputStream( fis );

            FileOutputStream fos = new FileOutputStream( tempFile );
            bos = new BufferedOutputStream( fos );

            while( ( b = (byte)bis.read() ) != -1 )
            {
                if( b == sourceBytes[foundLength] )
                {
                    foundLength++;
                    if( foundLength == sourceBytes.length )
                    {
                        //Full match
                        count++;
                        bos.write( targetBytes, 0, targetBytes.length );
                        foundLength = 0;
                    }
                }
                else
                if( foundLength > 0 )
                {
                    //Partial match
                    bos.write( sourceBytes, 0, foundLength );
                    foundLength = 0 ;
                }
                else
                {
                    //No match
                    bos.write( b );
                }
            }
        }finally{
            if( bis != null ) bis.close();
            if( bos != null ) bos.close();

            if( count > 0 )
            {
                move( tempFile, sourceFile );
            }
        }
        return count;//moon return   .
    }

    /**
	 * Copy.
	 *
	 * @param sourceFileName
	 *            the source file name
	 * @param targetFileName
	 *            the target file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void copy( String sourceFileName, String targetFileName ) throws IOException
    {
        copy( sourceFileName, targetFileName, null, false );
    }

    /**
	 * Copy.
	 *
	 * @param sourceFileName
	 *            the source file name
	 * @param targetFileName
	 *            the target file name
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void copy( String sourceFileName, String targetFileName, String filterName, boolean recursive ) throws IOException
    {
        if( sourceFileName == null )
        {
            throw new IOException( "Source file name is not set." );
        }

        if( targetFileName == null )
        {
            throw new IOException( "Target file name is not set." );
        }

        copy( new File( sourceFileName ), new File( targetFileName ),
            newFileFilter( filterName ), recursive );
    }

    /**
	 * Copy.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void copy( File sourceFile, File targetFile ) throws IOException
    {
        copy( sourceFile, targetFile, null, false );
    }

    /**
	 * Copy.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void copy( File sourceFile, File targetFile, FileFilter filter, boolean recursive ) throws IOException
    {
        if( sourceFile == null )
        {
            throw new IOException( "Source file is not set." );
        }

        if( targetFile == null )
        {
            throw new IOException( "Target file is not set." );
        }

        if( !sourceFile.exists() )
        {
            throw new IOException( "Source file " + sourceFile.getName() + " does not exist." );
        }

        copy( sourceFile, targetFile, filter, recursive, false );
    }

    /**
	 * Copy.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static void copy( File sourceFile, File targetFile, FileFilter filter, boolean recursive, boolean first ) throws IOException
    {
        if( sourceFile.isDirectory() )
        {
            if( !targetFile.exists() )
            {
                targetFile.mkdirs();
            }

            if( first || recursive )
            {
                File[] files = sourceFile.listFiles();
                for( int index = 0; files != null && index < files.length; index++ )
                {
                    copy( files[index], new File( targetFile + "/" + files[index].getName() ), filter, recursive );
                }
            }
        }
        else
        {
            if( filter != null && !filter.accept( sourceFile ) )
            {
                return;
            }

            File parent = targetFile.getParentFile();
            if( !parent.exists() )
            {
                parent.mkdirs();
            }
            else
            if( targetFile.exists() && targetFile.isDirectory() )
            {
                throw new IOException( "Target file " + targetFile.getName() + " cannot be a directory." );
            }
            else
            if( targetFile.exists() )
            {
                targetFile.delete();
            }

            copyFile( sourceFile, targetFile );
        }
    }

    /**
	 * Copy file.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static void copyFile( File sourceFile, File targetFile ) throws IOException
    {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;

        if (sourceFile.getAbsolutePath().equals(targetFile.getAbsolutePath()))
        {
            throw new IOException("The file cannot be copied onto itself: "+sourceFile.getAbsolutePath());
        }

        try
        {
            FileInputStream fis = new FileInputStream( sourceFile );
            bis = new BufferedInputStream( fis );

            FileOutputStream fos = new FileOutputStream( targetFile );
            bos = new BufferedOutputStream( fos );

            int i = -1;

            while ( ( i = bis.read() ) != -1 )
            {
                bos.write( i );
            }
        }
        finally
        {
            if( bis != null ) bis.close();
            if( bos != null ) bos.close();
        }
    }

    /**
	 * Move.
	 *
	 * @param sourceFileName
	 *            the source file name
	 * @param targetFileName
	 *            the target file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void move( String sourceFileName, String targetFileName ) throws IOException
    {
        move( sourceFileName, targetFileName, null, false );
    }

    /**
	 * Move.
	 *
	 * @param sourceFileName
	 *            the source file name
	 * @param targetFileName
	 *            the target file name
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void move( String sourceFileName, String targetFileName, String filterName, boolean recursive ) throws IOException
    {
        copy( sourceFileName, targetFileName, filterName, recursive );
        delete( sourceFileName, filterName, recursive );
    }

    /**
	 * Move.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void move( File sourceFile, File targetFile ) throws IOException
    {
        move( sourceFile, targetFile, null, false );
    }

    /**
	 * Move.
	 *
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void move( File sourceFile, File targetFile, FileFilter filter, boolean recursive ) throws IOException
    {
        copy( sourceFile, targetFile, filter, recursive );
        delete( sourceFile, filter, recursive );
    }

    /**
	 * Delete directory.
	 *
	 * @param fileName
	 *            the file name
	 * @param filesPresent
	 *            the files present
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void deleteDirectory( String fileName, boolean filesPresent ) throws IOException
    {
        if( !filesPresent && findFiles( fileName, "*", true ).size() == 0 )
        {
            delete( fileName );
        }
        else
        {
            delete( fileName );
        }
    }

    /**
	 * Delete.
	 *
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    /*
    public static void delete( String fileName ) throws IOException
    {
        delete( fileName, null, false );
    }
    */
    public static boolean delete( String fileName ) throws IOException
    {
    	Path p = Paths.get(fileName);
		if(Files.isExecutable(p)){
			Files.delete(p);
			return true;
		}
		return false;
    }

   /**
	 * Delete.
	 *
	 * @param fileName
	 *            the file name
	 * @param filterName
	 *            the filter name
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void delete( String fileName, String filterName, boolean recursive ) throws IOException
    {
        if( fileName == null )
        {
            throw new IOException( "File Name is not set." );
        }

        delete( new File( fileName ), newFileFilter( filterName ), recursive );
    }

    /**
	 * Delete.
	 *
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void delete( File file ) throws IOException
    {
        delete( file, null, false );
    }

    /**
	 * Delete.
	 *
	 * @param file
	 *            the file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void delete( File file, FileFilter filter, boolean recursive ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File is not set." );
        }

        delete( file, filter, recursive, true );
    }

    /**
	 * Delete.
	 *
	 * @param file
	 *            the file
	 * @param filter
	 *            the filter
	 * @param recursive
	 *            the recursive
	 * @param first
	 *            the first
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    protected static void delete( File file, FileFilter filter, boolean recursive, boolean first ) throws IOException
    {
        if( file.isDirectory() )
        {
            if( first || recursive )
            {
                File[] files = file.listFiles();
                for( int index = 0; files != null && index < files.length; index++ )
                {
                    delete( files[index], filter, recursive, false );
                }
            }

            try
            {
                file.delete();
            }
            catch( Exception e )
            {
                return;
            }

        }
        else
        {
            if( filter != null && !filter.accept( file ) ) return;

            try
            {
                file.delete();
            }
            catch( Exception e )
            {
                return;
            }
        }
    }

    /**
	 * New file filter.
	 *
	 * @param filterName
	 *            the filter name
	 * @return the file filter
	 */
    public static FileFilter newFileFilter( String filterName )
    {
        if( filterName == null ) return null;

        return new DefaultFileFilter( filterName );
    }

    /**
	 * The Class DefaultFileFilter.
	 *
	 * @author
	 * @version 1.0
	 * @see <pre>
	 * (Modification Information)
	 *
	 *
	 * ---------------  ----------------  ---------------------------
	 * 2015. 1. 15
	 *
	 * </pre>
	 * @since 2015. 1. 15
	 */
    public static class DefaultFileFilter implements FileFilter
    {

        /**
		 * Instantiates a new default file filter.
		 *
		 * @param filterName
		 *            the filter name
		 */
        public DefaultFileFilter( String filterName )
        {
            this.filterName = filterName;

            StringTokenizer st = new StringTokenizer( filterName, " " );

            String filter = null;
            filters = new String[ st.countTokens() ];
            comparitors = new String[ st.countTokens() ];

            for( int index = 0; st.hasMoreTokens(); index++ )
            {
                filter = st.nextToken();

                filters[index] = filter;

                if( filter.startsWith( "*" ) && filter.endsWith( "*" ) )
                {
                    comparitors[index] = null;
                }
                else
                if( filter.startsWith( "*" ) )
                {
                    comparitors[index] = filter.substring( 1, filter.length() );
                }
                else
                if( filter.endsWith( "*" ) )
                {
                    comparitors[index] = filter.substring( 0, filter.length() - 1 );
                }
                else
                {
                    comparitors[index] = filter;
                }

                if( comparitors[index] != null && comparitors[index].length() == 0 )
                {
                    throw new IllegalArgumentException( "Use * in filter " + filterName );
                }
            }
        }

        /* (non-Javadoc)
         * @see java.io.FileFilter#accept(java.io.File)
         */
        public boolean accept( File pathName )
        {
            if( pathName == null ) return false;

            if( pathName.isDirectory() ) return true;

            String name = pathName.getName();

            for( int index = 0; index < filters.length; index++ )
            {
                if( filters[index].startsWith( "*" ) && filters[index].endsWith( "*" ) )
                {
                    return true;
                }
                else
                if( filters[index].startsWith( "*" ) )
                {
                    if( name.endsWith( comparitors[index] ) ) return true;
                }
                else
                if( filters[index].endsWith( "*" ) )
                {
                    if( name.startsWith( comparitors[index] ) ) return true;
                }
                else
                {
                    if( name.equals( comparitors[index] ) ) return true;
                }
            }

            return false;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString()
        {
            return filterName;
        }

        protected String filterName;
        protected String[] filters;
        protected String[] comparitors;
    }

    /**
	 * Write.
	 *
	 * @param file
	 *            the file
	 * @param text
	 *            the text
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void write( File file, String text ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File is not set." );
        }

        if( text == null )
        {
            throw new IOException( "Text is not set." );
        }

        BufferedOutputStream bos = null;
        try
        {
            FileOutputStream fos = new FileOutputStream( file );
            bos = new BufferedOutputStream( fos );

            byte[] bytes = text.getBytes();
            bos.write( bytes, 0, bytes.length );
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw new IOException(ex);
        } finally {
            if( bos != null ) bos.close();
        }
    }

    /**
	 * Write.
	 *
	 * @param file
	 *            the file
	 * @param text
	 *            the text
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    public static void write( File file, byte [] text ) throws IOException
    {
        if( file == null )
        {
            throw new IOException( "File is not set." );
        }

        if( text == null )
        {
            throw new IOException( "Text is not set." );
        }

        BufferedOutputStream bos = null;
        try {
            FileOutputStream fos = new FileOutputStream( file );
            bos = new BufferedOutputStream( fos );

            bos.write( text, 0, text.length );
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw new IOException(ex);
        } finally {
            if( bos != null ) bos.close();
        }
    }

    /**
	 * Gets the file full name.
	 *
	 * @param path
	 *            the path
	 * @return the file full name
	 */
     public static String getFileFullName( String path )
     {
        if( path == null ) return "";

        int index = path.lastIndexOf( '/' );

        if( index < 0 ) return path;

        return path.substring( index+1 );
     }

     /**
		 * Gets the file name.
		 *
		 * @param path
		 *            the path
		 * @return the file name
		 */
      public static String getFileName( String path )
      {
         if( path == null )
        	 return null;

         int index = path.lastIndexOf( '/' );

         if( index >= 0 )
        	 path = path.substring(index + 1);

         int index1 = path.lastIndexOf( '.' );
         if( index1 < 0 )
        	 return path;
         else
        	 return path.substring(0, index1);
      }


    /**
	 * Gets the extension.
	 *
	 * @param fileName
	 *            the file name
	 * @return the extension
	 */
    public static String getExtension( String fileName )
    {
        if( fileName == null ) return "";

        int index = fileName.lastIndexOf( '.' )+1;

        if( index > 0 && index < fileName.length() )
        {
            return fileName.substring( index );
        }

        return null;
    }

    /**
	 * Gets the path.
	 *
	 * @param fileName
	 *            the file name
	 * @return the path
	 */
    public static String getPath( String fileName )
    {
        if( fileName == null ) return "";

        int index = fileName.lastIndexOf( '/' );

        if( index > 0 && index < fileName.length() )
        {
            return fileName.substring(0, index );
        }

        return null;
    }

    /**
	 * Gets the file with no extension.
	 *
	 * @param fileName
	 *            the file name
	 * @return the file with no extension
	 */
    public static String getFileWithNoExtension( String fileName )
    {
        if( fileName == null ) return "";

        int index = fileName.lastIndexOf( '.' );

        if( index >= 0 && index < fileName.length() )
        {
            return fileName.substring( 0, index );
        }

        return fileName;
    }

    /**
	 * Find files with date.
	 *
	 * @param directory
	 *            the directory
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the file[]
	 */
    public static File [] findFilesWithDate(String directory, String start, String end) {
    	// "C:/Program Files/Apache Software Foundation/Tomcat 7.0/logs"
    	File [] files = null;
    	File file = new File(directory);
    	try {
    		Date startDate = DateUtil.inst().stringToDate(start + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        	Date endDate = DateUtil.inst().stringToDate(end + " 23:59:59", "yyyy-MM-dd HH:mm:ss");

    		DateFileFilter dateFilter = new DateFileFilter(startDate, endDate);
    		files = file.listFiles(dateFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}

    	return files;
    }

    /**
	 * Find files with date and prefix.
	 *
	 * @param directory
	 *            the directory
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param prefix
	 *            the prefix
	 * @return the file[]
	 */
    public static File [] findFilesWithDateAndPrefix(String directory, String start, String end, String prefix) {
    	// "C:/Program Files/Apache Software Foundation/Tomcat 7.0/logs"
    	File [] files = null;
    	File file = new File(directory);
    	try {
    		Date startDate = DateUtil.inst().stringToDate(start + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
    		Date endDate = DateUtil.inst().stringToDate(end + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
    		DateAndPrefixFileFilter dateFilter = new DateAndPrefixFileFilter(startDate, endDate, prefix);
    		files = file.listFiles(dateFilter);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return files;
    }

    /**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
    public static void main(String args[]) {
    	File file = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/logs");
    	try {
			//List<File> files = FileUtil.findFiles(file, FileFilterUtils.trueFileFilter());
    		//List<File> files = FileUtil.findFiles(file, FileFilterUtils.ageFileFilter(DateUtil.stringToDate("2015-01-14", "yyyy-MM-dd"), true));
    		//List<File> files = FileUtil.findFiles(file, FileFilterUtils.and(FileFilterUtils.ageFileFilter(DateUtil.stringToDate("2015-01-13", "yyyy-MM-dd"), false), FileFilterUtils.ageFileFilter(DateUtil.stringToDate("2015-01-14", "yyyy-MM-dd"), true)));

    		Date startDate = DateUtil.inst().stringToDate("2015-01-12 00:00:00", "yyyy-MM-dd HH:mm:ss");
        	Date endDate = DateUtil.inst().stringToDate("2015-01-13 23:59:59", "yyyy-MM-dd HH:mm:ss");

    		DateFileFilter dateFilter = new DateFileFilter(startDate, endDate);
    		//List<File> files = FileUtil.findFiles(file, dateFilter);
    		File [] files = file.listFiles(dateFilter);
			for (File loopFile : files) {
				System.out.println(loopFile.getAbsolutePath() +":"+ loopFile.getName() +":"+ loopFile.length() +":"+ loopFile.lastModified());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}