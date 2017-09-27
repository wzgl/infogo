#include <stdio.h>
#include <stdlib.h>
#define MAXN 20

/*
*����쳲���������
* */
void Fibonacci(int *f)
{
    int i;
    f[0] = 1;
    f[1] = 1;
    for (i = 2; i < MAXN; ++i)
        f[i] = f[i - 2] + f[i - 1];
}

/*
* ����
* */
int Fibonacci_Search(int *a, int key, int n)
{
    int i, low = 0, high = n - 1;
    int mid = 0;
    int k = 0;
    int F[MAXN];
    Fibonacci(F);
    while (n > F[k] - 1)          //�����n��쳲������е�����
        ++k;
    for (i = n; i < F[k] - 1; ++i) //�����鲹ȫ
        a[i] = a[high];
    while (low <= high)
    {
        mid = low + F[k - 1] - 1;  //����쳲��������н��лƽ�ָ�
        if (a[mid] > key)
        {
            high = mid - 1;
            k = k - 1;
        }
        else if (a[mid] < key)
        {
            low = mid + 1;
            k = k - 2;
        }
        else
        {
            if (mid <= high) //���Ϊ�����ҵ���Ӧ��λ��
                return mid;
            else
                return -1;
        }
    }
    return 0;
}

int main()
{
    int a[MAXN] = { 5, 15, 19, 20, 25, 31, 38, 41, 45, 49, 52, 55, 57 };
    int k, res = 0;
    printf("������Ҫ���ҵ�����:\n");
    scanf("%d", &k);
    res = Fibonacci_Search(a, k, 13);
    if (res != -1)
        printf("������ĵ�%d��λ���ҵ�Ԫ��:%d\n", res + 1, k);
    else
        printf("δ���������ҵ�Ԫ��:%d\n", k);
    return 0;
}
