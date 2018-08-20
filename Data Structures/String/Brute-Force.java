//Brute-Force算法

public int indexof_BF_Count(MyString subStr, int start) { //统计Brute-Force算法查找子串的比较次数并返回
    int i = start,j = 0,v;
    int count = 0;
    
    while(i < this.length() && j < subStr.length()) {
        if(this.charAt(i) == subStr.charAt(j)) {
            i++;
            j++;
        }
        else{
            i = i - j + 1;
            j = 0;
        }
        count++    //统计比较次数
    }
    return count;   //返回
}
