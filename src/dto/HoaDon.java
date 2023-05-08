
package dto;

import java.util.Date;


public class HoaDon {
    private int MaHD;
    private Date NgayLap;

    public HoaDon(int MaHD, Date NgayLap) {
        this.MaHD = MaHD;
        this.NgayLap = NgayLap;
    }

    public int getMaHD() {
        return MaHD;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }
    
    
}
